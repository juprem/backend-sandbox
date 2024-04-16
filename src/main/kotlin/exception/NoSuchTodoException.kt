package exception

class NoSuchTodoException(private val id: String): NoSuchException("No such todo for id : $id") {
    override fun type() = "no-such-todo"
    override fun title() = "Todo not found"
    override fun code() = "NO_SUCH_TODO"
    override fun case() = "NO_SUCH_TODO"
    override fun id() = id
}
