package sc.ma

import scala.reflect.macros.whitebox.Context
import scala.language.experimental.macros

import play.api.libs.json._
import play.api.libs.json.Json.JsValueWrapper

object Json {

	//	def $(fields: (String, JsValueWrapper)*): JsObject = Json.obj(fields: _*)

	def $(fields: (String, JsValueWrapper)*): JsObject = macro jsonObj_impl

	def jsonObj_impl(c: Context)(fields: c.Expr[(String, JsValueWrapper)]*): c.Expr[JsObject] = {
		import c.universe._
		val jsonTree = q"""
			import play.api.libs.json._
			import play.api.libs.json.Json.JsValueWrapper
			Json.obj(${fields.map(_.tree): _*})
		"""
		c.Expr[JsObject](jsonTree)
	}

}
