package admin

import java.sql.{Date, Timestamp}

case class QuizRequest
(
  name: String,
  startDate: Date,
  endDate: Date,
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