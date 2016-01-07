- A Document : Header* Definitoin*
	- A Header :Include | CppInclude | Namespace
		- Thrift Include ：'include' Literal
		- C++ Include: 'cpp_include' Literal
		- Namespace ::=
		```
		( 'namespace' ( NamespaceScope Identifier ) |
                                        ( 'smalltalk.category' STIdentifier ) |
                                        ( 'smalltalk.prefix' Identifier ) ) |
                          ( 'php_namespace' Literal ) |
                          ( 'xsd_namespace' Literal )
		```

- Struct          ::=  'struct' Identifier 'xsd_all'? '{' Field* '}'
- Enum            ::=  'enum' Identifier '{' (Identifier ('=' IntConstant)? ListSeparator?)* '}'
-  Const           ::=  'const' FieldType Identifier '=' ConstValue ListSeparator?
- Exception       ::=  'exception' Identifier '{' Field* '}'


- Service         ::=  'service' Identifier ( 'extends' Identifier )? '{' Function* '}'


- Field : FieldID? FieldReq? FieldType Identifier
	- FieldID : IntConstant:
	- FieldReq : 'required' | 'optional'



- Types
	- FieldType : BaseType | ContainerType | Identifier
	- DefinitionType = BaseType | ContainerType
	- BaseType : 'bool','byte','i16','i32','i64','double','string','binary','slist'
	- ContainerType : MapType | SetType | ListType
	- MapType : 'map' CppType? '<' FieldType ',' FieldType '>'
	- SetType : 'set' CppType? '<' FieldType '>'
	- ListType : 'list' '<' FieldType '>' CppType?
	- CppType : 'cpp_type' Literal

- Identifier
	- Identifier ::=  ( Letter | '_' ) ( Letter | Digit | '.' | '_' )*
	- STIdentifier ::=  ( Letter | '_' ) ( Letter | Digit | '.' | '_' | '-' )*


- Letters and Digits
	- Letter	::=  ['A'-'Z'] | ['a'-'z']
	- Digit		::=  ['0'-'9']