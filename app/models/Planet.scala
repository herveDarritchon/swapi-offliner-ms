package models

/**
  * Created by Hervé Darritchon on 17/01/2016.
  *
  */
case class Planet(name:String,
                  rotation_period:String,
                  orbital_period:String,
                  diameter:String,
                  climate:String,
                  gravity:String,
                  terrain:String,
                  surface_water:String,
                  population:String,
                  residents:List[String],
                  films:List[String],
                  created:String,
                  edited:String,
                  url: String)