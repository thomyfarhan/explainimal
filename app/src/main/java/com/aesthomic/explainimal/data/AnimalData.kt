package com.aesthomic.explainimal.data

object AnimalData {

    val listAnimals: MutableList<Animal>
        get() {
            val list = mutableListOf<Animal>()
            list.addAll(animalData)
            return list
        }

    private val animalData = listOf (
        Animal("Cat"),
        Animal("Dog"),
        Animal("Monkey"),
        Animal("Bear"),
        Animal("Lion"),
        Animal("Tiger"),
        Animal("Deer"),
        Animal("Turtle"),
        Animal("Horse"),
        Animal("Elephant"),
        Animal("Dolphin"),
        Animal("Wolf"),
        Animal("Whale"),
        Animal("Panda"),
        Animal("Shark"),
        Animal("Eagle"),
        Animal("Snake"),
        Animal("Crocodile"),
        Animal("Duck"),
        Animal("Rhinoceros"),
        Animal("Bat"),
        Animal("Duck"),
        Animal("Penguin"),
        Animal("Hippopotamus"),
        Animal("Frog"),
        Animal("Hamster"),
        Animal("Gorilla"),
        Animal("Giraffe"),
        Animal("Owl"),
        Animal("Rabbit"),
        Animal("Cheetah"),
        Animal("Octopus"),
        Animal("Komodo"),
        Animal("Cow"),
        Animal("Sheep"),
        Animal("Pig"),
        Animal("Chicken"),
        Animal("Crow"),
        Animal("Jellyfish"),
        Animal("Lizard"),
        Animal("Butterfly"),
        Animal("Rat"),
        Animal("Zebra"),
        Animal("Squirrel"),
        Animal("Kangaroo")
    )
}