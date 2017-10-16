package app.model

object TicketRepository {
  type TicketId = Long

  private var tickets: Map[TicketId, Ticket] = Map(
    1L -> Issue(1L, "First Issue"),
    2L -> Issue(2L, "Second Issue"),
    3L -> Bug(3L, "First Bug", "sample"),
    4L -> Bug(4L, "Close Issue", "fixed", TicketStatus.Fixed)
  )

  def findAll(): Seq[Ticket] = tickets.values.toIndexedSeq // toSeqは遅延処理なのでデバッグに向かないため、説明の都合上止める

  def findById(id: TicketId): Option[Ticket] = tickets.get(id)

  def createIssue(title: String): Issue = {
    val newId = if (tickets.isEmpty) 1 else tickets.keys.max + 1
    val issue = Issue(newId, title)
    tickets = tickets + (newId -> issue)
    issue
  }

  def findBugsByStatus(status: TicketStatus): Seq[Bug] = {
    findAll().collect {
      case value@Bug(_, _, _, `status`) => value
    }
  }

  def fix(id: TicketId): Boolean = {
    val opening = findById(id).filter(_.status == TicketStatus.Open)
    opening.foreach(t =>
      tickets = tickets + (t.id -> t.updateStatus(TicketStatus.Open))
    )
    opening.isDefined
  }
}
