package user

import java.sql.{Date, Timestamp}

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

case class AnswerReceive
(
  questionId: Long,
  optionA: Boolean,
  optionB: Boolean,
  optionC: Boolean,
  optionD: Boolean,
  answer: Option[Boolean]
)


case class Answer
(
  id: Long,
  questionId: Long,
  email: String,
  optionA: Boolean,
  optionB: Boolean,
  optionC: Boolean,
  optionD: Boolean,
  result: Boolean,
  activeFlag: Boolean,
  createdOn: Timestamp,
  createdBy: String,
  modifiedOn: Timestamp,
  modifiedBy: String

)

case class QuizResultResponse
(
  quizId: Long,
  name: String,
  totalQuestions: Long,
  attendedQuestions: Long,
  result: Double
)