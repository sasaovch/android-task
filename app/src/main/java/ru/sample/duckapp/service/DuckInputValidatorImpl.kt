package ru.sample.duckapp.service

import ru.sample.duckapp.data.DuckInputValidator
import ru.sample.duckapp.domain.DuckContainer

class DuckInputValidatorImpl : DuckInputValidator {

    override fun validateInput(duckContainer: DuckContainer): Boolean {
        return duckContainer.code in 100..599
    }
}