package kotlin

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.ReplaySubject
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main() {
    Observable.fromCallable { println("subscribe on: ${Thread.currentThread().name}") }
        .subscribeOn(Schedulers.computation())
        .observeOn(Schedulers.from(Executors.newSingleThreadExecutor()))
        .subscribeOn(Schedulers.io())
        .flatMap { Observable.fromCallable {  println("subscribe on: ${Thread.currentThread().name}") } }
        .observeOn(Schedulers.from(Executors.newSingleThreadExecutor()))
        .observeOn(Schedulers.io())
        .subscribe {
            println("observe on: ${Thread.currentThread().name}")
        }
    Thread.sleep(10)
}

/**
 * kotlin.A subject can act as both observer and observable
 * When an observer subscribes to a behavior subject, it receives the last (or default) emitted value if any,
 * and then keeps receiving any subsequent events.
 * This can also be achieved without subjects, using replay(1).autoConnect(0) operator on an observable. The difference is
 * that, a behavior subject has a method [BehaviorSubject.value] which allows to peek to the last emitted value.
 *
 * Note: once the subject connects to the observable, the observable starts emitting values, whether or not an observer
 * has subscribed on the subject. In this sense, it converts the observer into a hot observer, just like
 * calling replay(1).autoConnect(0)
 */
private fun behaviorSubject() {
    // creating my own non-daemon thread
    val newSingleThreadExecutor = Executors.newSingleThreadExecutor()

    val observable = Observable.interval(0, 1, TimeUnit.SECONDS, Schedulers.from(newSingleThreadExecutor))

    val behaviorSubject = BehaviorSubject.createDefault<Long>(0)

    observable.subscribe(behaviorSubject)

    behaviorSubject.subscribe { println("first - $it") }

    Thread.sleep(3000)

    println("-------")
    println("value of subject now is: ${behaviorSubject.value}")

    behaviorSubject.subscribe { println("second - $it") }
}

/**
 * kotlin.A subject can act as both observer and observable
 * When an observer subscribes to a replay subject, it receives all the last emitted values if any,
 * and then keeps receiving any subsequent events.
 * This can also be achieved without subjects, using replay().autoConnect() operator on an observable. The difference is
 * that, a behavior subject has a method [ReplaySubject.getValue] which allows to peek to the last emitted value,
 * and [ReplaySubject.getValues] to get all values emitted before.
 *
 *  * Note: once the subject connects to the observable, the observable starts emitting values, whether or not an observer
 * has subscribed on the subject. In this sense, it converts the observer into a hot observer, just like
 * calling replay().autoConnect(0)
 */
private fun replaySubject() {
    // creating my own non-daemon thread
    val newSingleThreadExecutor = Executors.newSingleThreadExecutor()

    val observable = Observable.interval(0, 1, TimeUnit.SECONDS, Schedulers.from(newSingleThreadExecutor))

    val replaySubject = ReplaySubject.create<Long>(0)

    observable.subscribe(replaySubject)

    replaySubject.subscribe { println("first - $it") }

    Thread.sleep(3000)

    println("-------")
    println("value of subject now is: ${replaySubject.value}")

    replaySubject.subscribe { println("second - $it") }
}

/**
 * Cold observables are the ones that does its work only on subscription
 * Hot observables emits events (theoretically) constantly, whether an observer is listening or not
 * Use share to transform cold into hot. Use kotlin.behaviorSubject (or replay) to transform hot into cold
 */
private fun hotVsCold() {
    // creating my own non-daemon thread
    val newSingleThreadExecutor = Executors.newSingleThreadExecutor()

    val observable = Observable.range(1, 100000000)
        .sample(1, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.from(newSingleThreadExecutor))
        .observeOn(Schedulers.computation())
        .publish()          // changes cold into hot observable (shall not start from the beginning with each subscription)
        .refCount()         // holds the emission of events until the observers count reaches a specific number (default is 1)
//        .autoConnect()    // same as refCount, but works at most once (observer 3 will never connect)
//        .share()          // The same as .publish().refCount()

//    connects automatically, doesn't wait for any subscriptions (Really hot observable), it also doesn't
//    restart the observable after it's finished working, so observer 3 will never receive anything. (Really HOT!)
//    observable.connect()

    Thread.sleep(100)

    observable.subscribe { println("💃 $it from observer 1") }
    Thread.sleep(200)
    observable.subscribe { println("$it from observer 2") }

//    Thread.sleep(2000)
//    observable.subscribe { println("$it from observer 3") }

    // shutdown after finishing all scheduled tasks
    newSingleThreadExecutor.shutdown()
}

/**
 * maps one event into another event
 * Ex:- 0 5 10 15 20
 */
private fun map() {
    Observable.range(0, 5)
        .map { i -> "${i * 5} " }
        .blockingSubscribe { i -> print(i) }
}

/**
 * maps one event into zero or more events (into an observable)
 * guarantee the order of the events
 * Ex:- 0ABC.1ABC.2ABC.3ABC.4ABC.
 */
private fun concatMap() {
    Observable.range(0, 5)
        .concatMap { i ->
            Observable.just(i, "kotlin.A", "kotlin.B", "C", ".")
        }
        .blockingSubscribe { i -> print(i) }
}

/**
 * maps one event into zero or more events (into an observable)
 * does not guarantee the order of the events
 * Ex:- 0A1BABC.2ACB.C3.ABC.4ABC.
 * Ex:- 0ABC.21ABCABC.3.ABC.4ABC.
 */
private fun flatMap() {
    Observable.range(0, 5)
        .flatMap { i ->
            Observable.just(i, "kotlin.A", "kotlin.B", "C", ".")
        }
        .blockingSubscribe { i -> print(i) }
}

/**
 * maps one event into zero or more events (into an observable)
 * unsubscribe from the current source once a new source is attached
 * Ex:- 014ABC.
 * Ex:- 01ABC2A34ABC.
 * Ex:- 012ABC.34ABC.
 */
private fun switchMap() {
    Observable.range(0, 5)
        .switchMap { i ->
            Observable.just(i, "kotlin.A", "kotlin.B", "C", ".")
        }
        .blockingSubscribe { i -> print(i) }
}

