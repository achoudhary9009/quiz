package utils

import user.{QuizQuestionsResponse, QuizResponse}

//custom response case class
abstract class CustomHttpResponse
(
  code: String,
  message: String,
  queryResult: Any
)


//Case class for all quiz response
case class AllQuizHttpResponse
(code: String,message: String, queryResult: Vector[QuizResponse]) extends CustomHttpResponse(code,message,queryResult)

//Case class for all questions by quiz response
case class QuizQuestionsHttpResponse
(code: String,message: String, queryResult: Vector[QuizQuestionsResponse]) extends CustomHttpResponse(code,message,queryResult)