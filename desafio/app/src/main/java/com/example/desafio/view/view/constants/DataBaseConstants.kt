package com.example.desafio.view.view.constants

class DataBaseConstants private constructor(){
    object MOVIE{

        const val ID = "movieid"
        const val TABLE_NAME = "Movie"

        object COLUMNS{
            const val ID = "id"
            const val TITLE = "title"
            const val RATE = "rate"
            const val OPINION = "opinion"
        }
    }

}