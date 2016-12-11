case class OS(name: OS.Name.Name)

object OS {
  object Name extends Enumeration {
    type Name = Value
    val Windows, Linux, Mac = Value
  }

  val current = {
    val name = sys.props.get("os.name")
      .flatMap(n => OS.Name.values.find(v => n.contains(v.toString)))
      .getOrElse(sys.error("Unknown OS name!"))

    OS(name)
  }
}

