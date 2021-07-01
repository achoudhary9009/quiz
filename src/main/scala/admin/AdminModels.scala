package admin

import java.sql.{Date, Timestamp}

case class QuizRequest
(
  name: String,
  startDate: Date,
  endDate: Date,
  cratedBy: String
)
case class QuestionRequest
(
  quizId: Long,
  question: String,
  optionA: String,
  optionAFlag: Boolean,
  optionB: String,
  optionBFlag: Boolean,
  optionC: String,
  optionCFlag: Boolean,
  optionD: String,
  optionDFlag: Boolean,
  cratedBy: String

)


case class quiz
(
  id: Long,
  name: String,
  startDate: Date,
  endDate: Date,
  activeFlag: Boolean,
  createdOn: Timestamp,
  createdBy: String,
  modifiedOn: Timestamp,
  modifiedBy: String
)

case class Question
(
  id: Long,
  quizId: Long,
  question: String,
  optionA: String,
  optionAFlag: Boolean,
  optionB: String,
  optionBFlag: Boolean,
  optionC: String,
  optionCFlag: Boolean,
  optionD: String,
  optionDFlag: Boolean,
  activeFlag: Boolean,
  createdOn: Timestamp,
  createdBy: String,
  modifiedOn: Timestamp,
  modifiedBy: String

)