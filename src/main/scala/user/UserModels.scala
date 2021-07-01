package user

import java.sql.Date

case class QuizResponse
(
  id: Long,
  name: String,
  startDate: Date,
  endDate: Date
)
case class QuizQuestionsResponse
(
  id: Long,
  question: String,
  optionA: String,
  optionB: String,
  optionC: String,
  optionD: String
)