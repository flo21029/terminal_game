import kotlin.random.Random

class Theme {
    // Class that references the type of themes in the game
    var cars = mutableListOf<String>()
    var animals = listOf<String>()
}

fun main(){
    // Task: Build a command-line version of the Hangman game where players guess a hidden word.
    println("Before starting the game. Do you want to play it in debug mode? Y/N")
    var debugMode = false
    val answer = readln().lowercase().replace("\\s+".toRegex(), "")
    // We constantly ask for words until the word is guessed
    while (true){
        if (answer.equals("y")){
            debugMode = true
            break
        } else if (answer.equals("n")) {
            debugMode = false
            break
        } else {
            println("You must enter no more than one character between a and z")
            continue
        }
    }

    // Choose a random word from a list of words
    var themes = Theme()

    // We can add the elements of our Choice
    themes.cars = mutableListOf("Tesla", "BMW", "Ford", "Ferrari")
    themes.animals = listOf("Chipmunk", "Elephant", "Puddle")

    // We initialize the randomlychosen word as empty, to later assign a random value, thus we can keep this variable in the scope of the function
    var randomlyChosenWord = ""
    
    val theme = if (Random.nextInt(10) > 5) "Animals" else "Cars"

    println("Theme of the Game: " + theme)
    if (theme.equals("Animals")){
        randomlyChosenWord = themes.animals[Random.nextInt(themes.animals.size)].lowercase()
    } else if (theme.equals("Cars")){
        randomlyChosenWord = themes.cars[Random.nextInt(themes.cars.size)].lowercase()
    }

    // If debug mode is enables, we will display the hidden word randomly chosen
    if (debugMode){
        print("[Debug mode] The chosen letter is: " + randomlyChosenWord + "\n")
    }

    // We create an empty list that will start being filled with underscore characters, that later will be replaced one by one by the guessed words.
    val printedWordHidden = mutableListOf<String>()

    // Print the entire word as a sequence of underscore characters
    for (i in randomlyChosenWord){
        printedWordHidden.add("_")
    }

    // We constantly ask for words until the word is guessed
    while (true){
        for (i in printedWordHidden){
            print(i + " ")
        }

        // We request the user to input a letter
        println("\n\nGuess a letter: ")
        val letter = readln().lowercase().replace("\\s+".toRegex(), "")
        if (letter.length != 1 || !letter.all { it.isLetter() } ){
            println("You must enter no more than one character between a and z")
            continue
        }

        // If the letter was alredy guessed, we ask for another letter
        if (printedWordHidden.contains(letter)){
            println("You already guessed this letter, please enter a different letter.")
            continue
        }

        // IF the user found a letter, then we keep it
        if (randomlyChosenWord.contains(letter)){
            println("\n\nYou found me")

            // Add correctly guessed letter to the complete guessed letter list
            for ((index, value) in randomlyChosenWord.withIndex()){
                if (value.lowercase().equals(letter)){
                    printedWordHidden[index] = Character.toString(value)
                }
            }
            
        } else {
            // We display an error message when the word was not guessed
            println("\n\nNo success")
        }

        // Is the user guessed the entire word, we display a success message
        if (printedWordHidden.joinToString(separator="").equals(randomlyChosenWord)){
            println("The word is: " + randomlyChosenWord + ", You guessed the word, You win!")
            break
        }
    }
    
}