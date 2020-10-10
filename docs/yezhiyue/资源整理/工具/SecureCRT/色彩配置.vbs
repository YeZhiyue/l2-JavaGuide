#$language = "VBScript"
#$interface = "1.0"

crt.Screen.Synchronous = True

' This automatically generated script may need to be
' edited in order to work correctly.

Sub Main
	crt.Screen.Send "export PS1=" & chr(34) & "\[\033[01;31m\]\u\[\033[00m\]@\[\033[01;32m\]10.185.25.224\[\033[00m\][\[\033[01;33m\]\t\[\033[00m\]]:\[\033[01;34m\]\w\[\033[00m\]\n$ " & chr(34) & chr(13)
End Sub
