package klt.mdy.conversationviewwithpagination

sealed class MainAction{
    data class ChangeInputText(val text : String): MainAction()
    object ClearInputText: MainAction()
    object SendMessage : MainAction()
}
