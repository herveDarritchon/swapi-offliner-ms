package models

/**
  * Created by Hervé Darritchon on 17/01/2016.
  *
  */
case class Species(classification:String,
                   url:String,
                   hair_colors: String,
                   homeworld: String,
                   designation: String,
                   edited: String,
                   eye_colors: String,
                   people:List[String],
                   skin_colors: String,
                   language: String,
                   created: String,
                   name: String,
                   average_height: String,
                   films: List[String],
                   average_lifespan: String)