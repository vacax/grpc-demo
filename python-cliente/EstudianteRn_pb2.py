# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: EstudianteRn.proto
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x12\x45studianteRn.proto\x12\x0c\x65studiantern\"&\n\x11\x45studianteRequest\x12\x11\n\tmatricula\x18\x01 \x01(\x05\"H\n\x12\x45studianteResponse\x12\x11\n\tmatricula\x18\x01 \x01(\x05\x12\x0e\n\x06nombre\x18\x02 \x01(\t\x12\x0f\n\x07\x63\x61rrera\x18\x03 \x01(\t\"\x1f\n\x11\x45studianteBorrado\x12\n\n\x02ok\x18\x01 \x01(\x08\"G\n\x0fListaEstudiante\x12\x34\n\nestudiante\x18\x01 \x03(\x0b\x32 .estudiantern.EstudianteResponse\"\x07\n\x05\x45mpty\">\n\x0bMensajeChat\x12\x0f\n\x07usuario\x18\x01 \x01(\t\x12\x0f\n\x07mensaje\x18\x02 \x01(\t\x12\r\n\x05\x66\x65\x63ha\x18\x03 \x01(\t2\x85\x04\n\x0c\x45studianteRn\x12R\n\rgetEstudiante\x12\x1f.estudiantern.EstudianteRequest\x1a .estudiantern.EstudianteResponse\x12U\n\x0f\x63rearEstudiante\x12 .estudiantern.EstudianteResponse\x1a .estudiantern.EstudianteResponse\x12Z\n\x14\x61\x63tualizarEstudiante\x12 .estudiantern.EstudianteResponse\x1a .estudiantern.EstudianteResponse\x12V\n\x12\x65liminarEstudiante\x12\x1f.estudiantern.EstudianteRequest\x1a\x1f.estudiantern.EstudianteBorrado\x12\x45\n\x0flistaEstudiante\x12\x13.estudiantern.Empty\x1a\x1d.estudiantern.ListaEstudiante\x12O\n\x13\x63hatEntreEstudiante\x12\x19.estudiantern.MensajeChat\x1a\x19.estudiantern.MensajeChat(\x01\x30\x01\x62\x06proto3')



_ESTUDIANTEREQUEST = DESCRIPTOR.message_types_by_name['EstudianteRequest']
_ESTUDIANTERESPONSE = DESCRIPTOR.message_types_by_name['EstudianteResponse']
_ESTUDIANTEBORRADO = DESCRIPTOR.message_types_by_name['EstudianteBorrado']
_LISTAESTUDIANTE = DESCRIPTOR.message_types_by_name['ListaEstudiante']
_EMPTY = DESCRIPTOR.message_types_by_name['Empty']
_MENSAJECHAT = DESCRIPTOR.message_types_by_name['MensajeChat']
EstudianteRequest = _reflection.GeneratedProtocolMessageType('EstudianteRequest', (_message.Message,), {
  'DESCRIPTOR' : _ESTUDIANTEREQUEST,
  '__module__' : 'EstudianteRn_pb2'
  # @@protoc_insertion_point(class_scope:estudiantern.EstudianteRequest)
  })
_sym_db.RegisterMessage(EstudianteRequest)

EstudianteResponse = _reflection.GeneratedProtocolMessageType('EstudianteResponse', (_message.Message,), {
  'DESCRIPTOR' : _ESTUDIANTERESPONSE,
  '__module__' : 'EstudianteRn_pb2'
  # @@protoc_insertion_point(class_scope:estudiantern.EstudianteResponse)
  })
_sym_db.RegisterMessage(EstudianteResponse)

EstudianteBorrado = _reflection.GeneratedProtocolMessageType('EstudianteBorrado', (_message.Message,), {
  'DESCRIPTOR' : _ESTUDIANTEBORRADO,
  '__module__' : 'EstudianteRn_pb2'
  # @@protoc_insertion_point(class_scope:estudiantern.EstudianteBorrado)
  })
_sym_db.RegisterMessage(EstudianteBorrado)

ListaEstudiante = _reflection.GeneratedProtocolMessageType('ListaEstudiante', (_message.Message,), {
  'DESCRIPTOR' : _LISTAESTUDIANTE,
  '__module__' : 'EstudianteRn_pb2'
  # @@protoc_insertion_point(class_scope:estudiantern.ListaEstudiante)
  })
_sym_db.RegisterMessage(ListaEstudiante)

Empty = _reflection.GeneratedProtocolMessageType('Empty', (_message.Message,), {
  'DESCRIPTOR' : _EMPTY,
  '__module__' : 'EstudianteRn_pb2'
  # @@protoc_insertion_point(class_scope:estudiantern.Empty)
  })
_sym_db.RegisterMessage(Empty)

MensajeChat = _reflection.GeneratedProtocolMessageType('MensajeChat', (_message.Message,), {
  'DESCRIPTOR' : _MENSAJECHAT,
  '__module__' : 'EstudianteRn_pb2'
  # @@protoc_insertion_point(class_scope:estudiantern.MensajeChat)
  })
_sym_db.RegisterMessage(MensajeChat)

_ESTUDIANTERN = DESCRIPTOR.services_by_name['EstudianteRn']
if _descriptor._USE_C_DESCRIPTORS == False:

  DESCRIPTOR._options = None
  _ESTUDIANTEREQUEST._serialized_start=36
  _ESTUDIANTEREQUEST._serialized_end=74
  _ESTUDIANTERESPONSE._serialized_start=76
  _ESTUDIANTERESPONSE._serialized_end=148
  _ESTUDIANTEBORRADO._serialized_start=150
  _ESTUDIANTEBORRADO._serialized_end=181
  _LISTAESTUDIANTE._serialized_start=183
  _LISTAESTUDIANTE._serialized_end=254
  _EMPTY._serialized_start=256
  _EMPTY._serialized_end=263
  _MENSAJECHAT._serialized_start=265
  _MENSAJECHAT._serialized_end=327
  _ESTUDIANTERN._serialized_start=330
  _ESTUDIANTERN._serialized_end=847
# @@protoc_insertion_point(module_scope)
