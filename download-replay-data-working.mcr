LABEL : checkOnlineStatus
IF PIXEL COLOR EQUALS : 63 : 860 : 9498256
IF PIXEL COLOR EQUALS : 180 : 860 : 13160660
OPEN FILE : C:\Date.bat : 
GOTO : download-start
ELSE
DELAY : 5000
GOTO : checkOnlineStatus
ENDIF
DELAY : 5000
GOTO : checkOnlineStatus
ENDIF
LABEL : download-start
DELAY : 120
Mouse : 63 : 860 : Move : 0 : 0
Mouse : 63 : 860 : LeftButtonDown : 0 : 0
DELAY : 62
Mouse : 63 : 860 : Move : 0 : 0
Mouse : 63 : 860 : LeftButtonUp : 0 : 0
DELAY : 10
Keyboard : AltLeft : SystemKeyDown
Keyboard : F : SystemKeyDown
DELAY : 74
Keyboard : AltLeft : KeyUp
Keyboard : F : KeyUp
DELAY : 25
Keyboard : U : KeyDown
DELAY : 87
Keyboard : U : KeyUp
Keyboard : D : KeyDown
DELAY : 62
Keyboard : D : KeyUp
DELAY : 20
Keyboard : Return : KeyDown
DELAY : 62
Keyboard : Return : KeyUp
DELAY : 20
LABEL : repeate
REPEAT : 200 : 0
GOTO : checkOnlineStatus
ENDREPEAT
