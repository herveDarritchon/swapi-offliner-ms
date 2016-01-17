package models

/**
  * Created by Hervé Darritchon on 17/01/2016.
  *
  */
case class Film(title: String,
                episode_id: Int,
                opening_crawl: String,
                director: String,
                producer: String,
                release_date: String,
                characters: List[String],
                planets: List[String],
                starships: List[String],
                vehicles: List[String],
                species: List[String],
                url: String)
