package klt.mdy.conversationviewwithpagination.view

sealed class MainAction {
    data class ChangeInputText(val text: String) : MainAction()
    object ClearInputText : MainAction()
    object SendMessage : MainAction()
    object ReceiveMessage : MainAction()
    object ClickNewMessage : MainAction()
    object ClearNewMessages : MainAction()
}
