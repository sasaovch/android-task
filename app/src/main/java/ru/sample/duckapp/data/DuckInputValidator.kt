package ru.sample.duckapp.data

import ru.sample.duckapp.domain.DuckContainer

interface DuckInputValidator {
    fun validateInput(duckContainer: DuckContainer): Boolean
}