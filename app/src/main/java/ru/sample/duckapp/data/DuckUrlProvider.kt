package ru.sample.duckapp.data

import ru.sample.duckapp.domain.DuckContainer

interface DuckUrlProvider {
    fun getDuckUrl(duckContainer: DuckContainer): String
}