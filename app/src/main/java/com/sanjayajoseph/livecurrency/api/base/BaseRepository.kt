package com.sanjayajoseph.livecurrency.api.base

import com.sanjayajoseph.livecurrency.api.interfaces.APICurrenciesInterface
import com.sanjayajoseph.livecurrency.api.interfaces.ApiCallBack
import com.sanjayajoseph.livecurrency.api.interfaces.Unsubscribe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/


open class BaseRepository(
    @PublishedApi internal val service: APICurrenciesInterface,
    @PublishedApi internal val compositeDisposable: CompositeDisposable
) : Unsubscribe {

    override fun unSubscribe() {
        compositeDisposable.clear()
    }

    inline fun <reified T : Any> fetchData(
        function: Observable<T>,
        callBack: ApiCallBack<T>
    ) {

        compositeDisposable.add(function
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callBack.onStart() }
            .subscribeWith(subscribe(callBack))
        )
    }

    fun <T> subscribe(callback: ApiCallBack<T>): DisposableObserver<T> {
        return object : DisposableObserver<T>() {
            override fun onComplete() {
                callback.onComplete()
            }

            override fun onNext(t: T) {
                callback.onSucess(t)
            }

            override fun onError(e: Throwable) {
                callback.onError(e)
            }
        }
    }

}