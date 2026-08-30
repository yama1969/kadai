@echo off

rem 各チームのディレクトリ
set team1=goodsample
set team2=badsample

rem チームディレクトリのルート
set teamdir=%~dp0..\tester

rem Javaのpath設定
set path=\10_programs\01_deverop\jdk1.7.0_80(astahに必要)\bin;%path%

rem テストするクラスの名前
rem (テスターのディレクトリ名[class名_tester],テスターのクラス名[class名Tester])
set class1=Accel
set class2=Brake
set class3=Car
set class4=Handle
set class5=Tire

rem テスト開始 (setlocal enabledelayedexpansionについては最下部のコメントを読んで)
setlocal enabledelayedexpansion

set team_i=1
set class_i=1

:FOREACH_TEAM_CLASS

set curteam=!team%team_i%!
set curclass=!class%class_i%!
set classpath=%teamdir%\%curteam%;
set classpath=%~dp0%curclass%_tester;%classpath%

if defined curteam (
  if defined curclass (
    echo.
    echo *** %curteam%チームの%curclass% *****************************
    echo.
    java %curclass%Tester
    set /a class_i+=1
    goto :FOREACH_TEAM_CLASS
  )

  set class_i=1
  set /a team_i+=1
  goto :FOREACH_TEAM_CLASS
)

endlocal

pause


rem バッチ処理のメモ
rem   「遅延環境変数」を利用している。(setlocal enabledelayedexpansion ～ endlocalの区間)
rem   遅延環境変数とは、
rem   「環境変数の値の評価を、行を読み込む時ではなく、行を実行する時に行う」仕組み。
rem   例えば、下記のようにif文を使うと、画面には"Yama"と表示される。
rem     set name=Yama
rem     if defined name (
rem       set name=Hiro
rem       echo %name%
rem     )

rem   これは、if文やfor文は()も含めて1行のため、バッチファイル実行時、
rem   if文の部分は下記のように読み込まれ、
rem     if defined name (set name=Hiro[改行] echo %name%[改行] )
rem   読み込まれた時点では、直上のset name=Yamaが実行された直後なので、
rem   解釈としては下記のようになるため。
rem     if defined name (set name=Hiro[改行] echo Yama[改行] )
rem   このように解釈された後に実行されるので、"Yama"と表示される。

rem   読込時ではなく実行時に変数nameを評価するのが、遅延環境変数。
rem   次のように利用する。
rem     setlocal enabledelayedexpansion
rem     set name=Yama
rem     if defined name (
rem       set name=Hiro
rem       echo !name!
rem     )
rem     endlocal

rem   こうすると、if文の部分は下記のように読み込まれ、
rem     if defined name (set name=Hiro[改行] echo !name![改行] )
rem   !name!は実行するときに評価されるので、echo !name!を実行するときに、
rem     echo Hiro
rem   となり、"Hiro"が表示される。

rem   遅延環境変数は以上のようなものなので、
rem   利用場面はif文やfor文の中で変数の値を書き換えながら処理をしたいとき。

rem   他には、変数名の中で変数を使いたいときにも応用できる。
rem   例えば、下のプログラムのように"name1"という変数名の"1"を別変数で指定したいとき、
rem   「%name%index%%」と書いてしまうと、[%name%]と[index]と[%%]と解釈されてしまう。
rem   そこで遅延環境変数を使えば、下記のように!～!の中に%～%をネストすることができる。
rem     setlocal enabledelayedexpansion
rem     set name1=Yama
rem     set name2=Hiro
rem     set index=1
rem     set currentname=!name%index%!
rem     endlocal

rem   上記プログラムのindexを配列の要素番号と見なせば、
rem   バッチで配列のようなものが使えることになる。(元々バッチには配列はない)
rem     setlocal enabledelayedexpansion
rem     set name[1]=Yama
rem     set name[2]=Hiro
rem     set index=1
rem     set currentname=!name[%index%]!
rem     set /a index+=1
rem     set currentname=!name[%index%]!
rem     endlocal

rem   ちなみに、%name[!index!]%だとcurrentnameが未定義になる。
rem   遅延環境変数の動作をよくよく考えれば納得いくのだが、山田には納得まで時間がかかる。
rem   また、setlocal enabledelayedexpansion ～ endlocalをできる限りローカルにしようと
rem   書き換えたら、とたんに動作しなくなった。実行中にOn/Offできるのは1回ずつのみのようである。

rem   以上

