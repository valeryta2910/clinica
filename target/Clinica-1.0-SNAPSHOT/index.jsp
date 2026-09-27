<%@page import="umariana.clinica.*, java.util.*" %>
<%
    GestionarPaciente gestionar = (GestionarPaciente) application.getAttribute("gestionar");
%>
<%
    Paciente pacienteEditar = null;
    String editId = request.getParameter("editId");
    if (editId != null) {
        int idAEditar = Integer.parseInt(editId);
        pacienteEditar = gestionar.obtenerPaciente(idAEditar);
    }
%>
<%
    Paciente pacienteVer = null;
    String viewId = request.getParameter("viewId");
    if (viewId != null) {
        int idAVer = Integer.parseInt(viewId);
        pacienteVer = gestionar.obtenerPaciente(idAVer);
    }
%>
<%@include file = "header.jsp" %>

<div class="container p-4"> 
    <% 
    String mensaje = (String) session.getAttribute("mensaje");
    if (mensaje != null) {
    %>
    <div class="alert alert-info"><% out.print(mensaje); %></div>
    <%
        session.removeAttribute("mensaje");
    }
    %>
    <div class="row">
        <div class="col-md-4">
            <div class="card card-body">
                <form action="recibePaciente" method="POST">
                    <input type="hidden" name="accion" value="<% if (pacienteEditar != null) { out.print("editar"); } else { out.print("guardar"); } %>">
                    <div class="form-group">
                        <input type="text" name="idPaciente" class="form-control" placeholder="id del paciente" 
                        value="<% if (pacienteEditar != null) { out.print(pacienteEditar.getIdPaciente()); } %>"
                        <% if (pacienteEditar != null) { out.print("readonly"); } %> autofocus required>
                    </div><br>
                    <div class="form-group">
                        <input type="text" name="nombre" class="form-control" placeholder="nombre del paciente" 
                        value="<% if (pacienteEditar != null) { out.print(pacienteEditar.getNombre()); } %>" required>
                    </div><br>
                    <div class="form-group">
                        <input type="text" name="apellido" class="form-control" placeholder="apellido del paciente"
                        value="<% if (pacienteEditar != null) { out.print(pacienteEditar.getApellido()); } %>" required>
                    </div><br>
                    <div class="form-group">
                        <textarea class="form-control" name="diagnostico" rows="3" placeholder="diagnostico"><% if (pacienteEditar != null) { out.print(pacienteEditar.getDiagnostico()); } %></textarea>
                    </div><br>
                    <input type="submit" class="btn btn-success btn-block" name="Guardar" 
                           value="<% if (pacienteEditar != null) { out.print("Actualizar"); } else { out.print("Guardar"); } %>">
                </form>  
            </div> <!-- card-body -->
        </div> <!-- col-md-4 -->
        <div class="col-md-8">
            <%
                if (pacienteVer != null) {
            %>
                <div class="alert alert-secondary">
                    <strong>Detalle del paciente</strong><br>
                    Id: <% out.print(pacienteVer.getIdPaciente()); %><br>
                    Nombre: <% out.print(pacienteVer.getNombre()); %><br>
                    Apellido: <% out.print(pacienteVer.getApellido()); %><br>
                    Diagnóstico: <% out.print(pacienteVer.getDiagnostico()); %>
                </div>
            <%
                }
            %>
            <div class="card card-body"> 
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Id</th>
                            <th scope="col">Nombres</th>
                            <th scope="col">Apellidos</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int contador=1;
                            for (Paciente p: gestionar.obtenerPacientes()){
                        %>
                        <tr>
                            <th scope="row"><% out.print(contador); %></th>
                            <td><% out.print(p.getIdPaciente()); %></td>
                            <td><% out.print(p.getNombre()); %></td>
                            <td><% out.print(p.getApellido()); %></td>
                            <td>
                                <a href="index.jsp?viewId=<% out.print(p.getIdPaciente());%> "class="btn btn-success"><i class="fa fa-eye"></i></a>
                                <a href="index.jsp?editId=<% out.print(p.getIdPaciente()); %>" class="btn btn-warning"><i class="fa fa-pen-to-square"></i></a>
                                <form action="recibePaciente" method="POST" style="display:inline;"
                                    onsubmit="return confirm('¿Estás seguro/a de eliminar este paciente?');">
                                    <input type="hidden" name="accion" value="eliminar">
                                    <input type="hidden" name="idPaciente" value="<% out.print(p.getIdPaciente()); %>">
                                    <button type="submit" class="btn btn-danger"><i class="fa fa-trash"></i></button>
                                </form>
                            </td>
                        </tr>
                        <%
                            contador++;
                            }
                        %>
                    </tbody>
                </table>
            </div> <!-- card-body -->
        </div> <!-- col-md-8 -->
    </div>
</div>

<%@include file = "footer.jsp" %>