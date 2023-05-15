package com.example.gitaarstemmer

enum class Noot {
    A, A_SHARP, B, C, C_SHARP, D, D_SHARP, E, F, F_SHARP, G, G_SHARP;

    override fun toString(): String {
        return super.toString().replace("_SHARP", "#")
    }
}