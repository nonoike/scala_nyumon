package app.model

trait Ticket {
  val id: Long
  val title: String
  var status: TicketStatus

  abstract def updateStatus(status: TicketStatus): Ticket
}

case class Issue(id: Long,
                 title: String,
                 status: TicketStatus = TicketStatus.Open
                ) extends Ticket {
  override def updateStatus(status: TicketStatus): Ticket = Issue(id, title, status)
}

case class Bug(id: Long,
               title: String,
               description: String,
               status: TicketStatus = TicketStatus.Open
              ) extends Ticket {
  override def updateStatus(status: TicketStatus): Ticket = Bug(id, title, description, status)
}
