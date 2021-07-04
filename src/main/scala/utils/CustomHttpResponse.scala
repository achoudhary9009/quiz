package utils

import user.{QuizQuestionsResponse, QuizResultResponse, QuizResponse}

//custom response case class
abstract class CustomHttpResponse
(
  code: String,
  message: String,
  queryResult: Any
)

abstract class CustomHttpMessageResponse
(
  code: String,
  message: String
)



//Case class for all quiz response
case class AllQuizHttpResponse
(code: String,message: String, queryResult: Vector[QuizResponse]) extends CustomHttpResponse(code,message,queryResult)

//Case class for all questions by quiz response
case class QuizQuestionsHttpResponse
(code: String,message: String, queryResult: Vector[QuizQuestionsResponse]) extends CustomHttpResponse(code,message,queryResult)
//Case class for all questions by quiz response
case class QuizRResultHttpResponse
(code: String,message: String, queryResult: Vector[QuizResultResponse]) extends CustomHttpResponse(code,message,queryResult)

//Case class for integer response
case class HttpMessageResponse
(code: String,message: String) extends CustomHttpMessageResponse(code,message)