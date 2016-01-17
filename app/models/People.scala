package models

/**
  * Created by Hervé Darritchon on 17/01/2016.
  *
  */
case class People(name: String,
                  height: String,
                  mass: String,
                  hair_color: String,
                  skin_color: String,
                  eye_color: String,
                  birth_year: String,
                  gender: String,
                  homeworld: String,
                  films: List[String],
                  species: List[String],
                  vehicles: List[String],
                  starships: List[String],
                  url: String)
