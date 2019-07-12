package com.tobiapplications.artista.utils.general


object ArtistaUrls {

    const val BASE_LAST_FM_URL = "http://ws.audioscrobbler.com/2.0/"

    // Services
    private const val SEARCH_ARTIST_URL = "?method=artist.search&artist="
    private const val TOP_ALBUMS_URL = "?method=artist.gettopalbums&artist=%s&page=%s&limit=%s"
    private const val ALBUM_TRACKS = "?method=album.getinfo&artist=%s&album=%s"

    fun getSearchArtistUrl(artist: String) : String {
        return SEARCH_ARTIST_URL.plus(artist)
    }

    fun getTopAlbumsUrl(artist: String, albumPage: Int, resultsPerPage: Int) : String {
        return String.format(TOP_ALBUMS_URL, artist, albumPage, resultsPerPage)
    }

    fun getAlbumTracksUrl(artist: String, album: String) : String {
        return String.format(ALBUM_TRACKS, artist, album)
    }
}

