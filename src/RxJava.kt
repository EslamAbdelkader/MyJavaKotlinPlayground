import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main() {
    hotVsCold()
}

/**
 * Cold observables are the ones that does its work only on subscription
 * Hot observables emits events (theoretically) constantly, whether an observer is listening or not
 * Use share to transform cold into hot. Use subjects (or replay) to transform hot into cold
 */
private fun hotVsCold() {
    // creating my own non-daemon thread
    val newSingleThreadExecutor = Executors.newSingleThreadExecutor()

    val observable = Observable.range(1, 100000000)
        .sample(1, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.from(newSingleThreadExecutor))
        .observeOn(Schedulers.computation())
        .publish()          // changes cold into hot observable (shall not start from the begining with each subscription)
        .refCount()         // holds the emission of events until the observers count reaches a specific number (default is 1)
//        .autoConnect()    // same as refCount, but works at most once (observer 3 will never connect)
//        .share()          // The same as .publish().refCount()

//    connects automatically, doesn't/**/ wait for any subscriptions (Really hot observable), it also doesn't
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
            Observable.just(i, "A", "B", "C", ".")
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
            Observable.just(i, "A", "B", "C", ".")
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
            Observable.just(i, "A", "B", "C", ".")
        }
        .blockingSubscribe { i -> print(i) }
}

