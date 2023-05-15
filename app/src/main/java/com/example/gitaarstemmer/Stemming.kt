package com.example.gitaarstemmer

import kotlinx.serialization.Serializable

@Serializable
class Stemming(val title: String, var selected: Boolean, val noten: Array<Noot>)