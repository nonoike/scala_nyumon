package app.model

sealed trait TicketStatus

object TicketStatus {

  case object Open extends TicketStatus

  case object Fixed extends TicketStatus

}
