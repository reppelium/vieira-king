(ns compiler.config
   (require [clojure.string :refer [lower-case]]))

(defn get-possibles-token
   []
   {
      :reserved-words [
         {:id 1  :name "Program" :function (fn [x] (= (lower-case x) "program"))}
         {:id 2  :name "Label" :function (fn [x] (= (lower-case x) "label"))}
         {:id 3  :name "Const" :function (fn [x] (= (lower-case x) "const"))}
         {:id 4  :name "Var" :function (fn [x] (= (lower-case x) "var"))}
         {:id 5  :name "Procedure" :function (fn [x] (= (lower-case x) "procedure"))}
         {:id 6  :name "Begin" :function (fn [x] (= (lower-case x) "begin"))}
         {:id 7  :name "End" :function (fn [x] (= (lower-case x) "end"))}
         {:id 8  :name "Integer" :function (fn [x] (= (lower-case x) "integer"))}
         {:id 9  :name "Array" :function (fn [x] (= (lower-case x) "array"))}
         {:id 10  :name "Of" :function (fn [x] (= (lower-case x) "of"))}
         {:id 11  :name "Call" :function (fn [x] (= (lower-case x) "call"))}
         {:id 12  :name "Goto" :function (fn [x] (= (lower-case x) "goto"))}
         {:id 13  :name "If" :function (fn [x] (= (lower-case x) "if"))}
         {:id 14  :name "Then" :function (fn [x] (= (lower-case x) "then"))}
         {:id 15  :name "Else" :function (fn [x] (= (lower-case x) "else"))}
         {:id 16  :name "While" :function (fn [x] (= (lower-case x) "while"))}
         {:id 17  :name "Do" :function (fn [x] (= (lower-case x) "do"))}
         {:id 18  :name "Repeat" :function (fn [x] (= (lower-case x) "repeat"))}
         {:id 19  :name "Until" :function (fn [x] (= (lower-case x) "until"))}
         {:id 20  :name "Readln" :function (fn [x] (= (lower-case x) "readln"))}
         {:id 21  :name "Writeln" :function (fn [x] (= (lower-case x) "writeln"))}
         {:id 22  :name "Or" :function (fn [x] (= (lower-case x) "or"))}
         {:id 23  :name "And" :function (fn [x] (= (lower-case x) "and"))}
         {:id 24  :name "Not" :function (fn [x] (= (lower-case x) "not"))}
         {:id 27  :name "For" :function (fn [x] (= (lower-case x) "for"))}
         {:id 28  :name "To" :function (fn [x] (= (lower-case x) "to"))}
         {:id 29  :name "Case" :function (fn [x] (= (lower-case x) "case"))}
         {:id 30  :name "+" :function (fn [x] (= x "+"))}
         {:id 31  :name "-" :function (fn [x] (= x "-"))}
         {:id 32  :name "*" :function (fn [x] (= x "*"))}
         {:id 33  :name "/" :function (fn [x] (= x "/"))}
         {:id 34  :name "[" :function (fn [x] (= x "["))}
         {:id 35  :name "]" :function (fn [x] (= x "]"))}
         {:id 36  :name "(" :function (fn [x] (= x "("))}
         {:id 37  :name ")" :function (fn [x] (= x ")"))}
         {:id 38  :name ":=" :function (fn [x] (= x ":="))}
         {:id 39  :name ":" :function (fn [x] (= x ":"))}
         {:id 40  :name "=" :function (fn [x] (= x "="))}
         {:id 41  :name ">" :function (fn [x] (= x ">"))}
         {:id 42  :name ">=" :function (fn [x] (= x ">="))}
         {:id 43  :name "<" :function (fn [x] (= x "<"))}
         {:id 44  :name "<=" :function (fn [x] (= x "<="))}
         {:id 45  :name "<>" :function (fn [x] (= x "<>"))}
         {:id 46  :name "," :function (fn [x] (= x ","))}
         {:id 47  :name ";" :function (fn [x] (= x ";"))}
         {:id 49  :name "." :function (fn [x] (= x "."))}
         {:id 50  :name ".." :function (fn [x] (= x ".."))}
         {:id 51  :name "$" :function (fn [x] (= x "$"))}
      ]
   :special-words [
         {:id 25  :name "Identificador" :function "logic.verify/identifier"}
         {:id 26  :name "Inteiro" :function "logic.verify/integer"}
         {:id 48  :name "Literal" :function "literal"}]})
