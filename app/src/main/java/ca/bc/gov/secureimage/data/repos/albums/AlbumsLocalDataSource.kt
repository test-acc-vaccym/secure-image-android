package ca.bc.gov.secureimage.data.repos.albums

import ca.bc.gov.secureimage.data.models.Album
import io.reactivex.Observable
import io.realm.Realm

/**
 * Created by Aidan Laing on 2017-12-14.
 *
 */
object AlbumsLocalDataSource : AlbumsDataSource {

    override fun saveAlbum(album: Album): Observable<Album> {
        return Observable.create { emitter ->
            val realm = Realm.getDefaultInstance()
            realm.executeTransaction {
                realm.copyToRealm(album)
                emitter.onNext(album)
            }
            realm.close()

            emitter.onComplete()
        }
    }

    override fun getAllAlbums(): Observable<ArrayList<Album>> {
        return Observable.create { emitter ->
            val realm = Realm.getDefaultInstance()
            realm.executeTransaction {
                val albums = realm.copyFromRealm(realm.where(Album::class.java).findAll())
                emitter.onNext(ArrayList(albums))
            }
            realm.close()

            emitter.onComplete()
        }
    }
}