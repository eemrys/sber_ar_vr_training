package com.example.exercise4

import android.widget.ImageView

fun ImageView.setMoviePoster(posterId: Int) {
    this.setImageResource(when(posterId) {
        0 -> R.drawable.poster_0
        1 -> R.drawable.poster_1
        2 -> R.drawable.poster_2
        3 -> R.drawable.poster_3
        4 -> R.drawable.poster_4
        5 -> R.drawable.poster_5
        6 -> R.drawable.poster_6
        7 -> R.drawable.poster_7
        else -> R.drawable.poster_8
    })
}

fun createMovieList(): List<Movie> {
    return listOf(
        Movie("La La Land", "Sebastian (Ryan Gosling) and Mia (Emma Stone) are drawn together by their common desire to do what they love. But as success mounts they are faced with decisions that begin to fray the fragile fabric of their love affair, and the dreams they worked so hard to maintain in each other threaten to rip them apart.", 0),
        Movie("Black Panther", "After his father\'s death, T\'Challa returns home to Wakanda to inherit his throne. However, a powerful enemy related to his family threatens to attack his nation.", 1),
        Movie("The Revenant", "Hugh Glass, a legendary frontiersman, is severely injured in a bear attack and is abandoned by his hunting crew. He uses his skills to survive and take revenge on his companion, who betrayed him.", 2),
        Movie("The Lord of the Rings: The Return of the King.", "The former Fellowship members prepare for the final battle. While Frodo and Sam approach Mount Doom to destroy the One Ring, they follow Gollum unaware of the path he is leading them to.", 3),
        Movie("Bohemian Rhapsody", "Queen, a British rock band, sets out to achieve musical superstardom. At the same time, lead singer Freddie Mercury faces personal struggles pertaining to his ego and sexuality.", 4),
        Movie("Spider-Man: Into the Spider-Verse", "After gaining superpowers from a spider bite, Miles Morales protects the city as Spider-Man. Soon, he meets alternate versions of himself and gets embroiled in an epic battle to save the multiverse.", 5),
        Movie("The Shape of Water", "Elisa, a lonely janitor, stumbles upon an amphibious creature that is held captive in a secret research facility. She then develops a unique relationship with the creature.", 6),
        Movie("Black Swan", "Nina, a ballerina, gets the chance to play the White Swan, Princess Odette. But she finds herself slipping into madness when Thomas, the artistic director, decides that Lily might fit the role better.", 7),
        Movie("Gravity", "Dr Ryan Stone, an engineer, and Matt Kowalski, an astronaut, are on a space mission together. However, when they are hit by high-speed space debris, they must find a way to return to Earth.", 8)
    )
}