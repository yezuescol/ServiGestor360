import { useState, useEffect } from 'react'

import TecnicoTable from '../components/TecnicoTable'

import {
    getTecnicos,
    crearTecnico,
    actualizarTecnico,
    eliminarTecnico
} from '../services/tecnicoService'

import {
    getDepartamentos,
    getMunicipiosPorDepartamento
} from '../services/colombiaService'

import '../styles/Cliente.css'

function Tecnico() {
    const [tecnico, setTecnico] = useState({
        nombres: '',
        apellidos: '',
        correo: '',
        telefono: '',
        especialidad: '',
        departamento: '',
        municipio: '',
        activo: true
    })

    const [tecnicos, setTecnicos] = useState([])
    const [departamentos, setDepartamentos] = useState([])
    const [municipios, setMunicipios] = useState([])
    const [modoEdicion, setModoEdicion] = useState(false)
    const [idEditando, setIdEditando] = useState(null)

    const especialidades = [
        'Soporte Técnico',
        'Redes',
        'Mantenimiento',
        'Instalación',
        'Configuración'
    ]

    const handleChange = (e) => {
        const { name, value } = e.target

        setTecnico({
            ...tecnico,
            [name]: value
        })
    }

    const obtenerTecnicos = async () => {
        try {
            const respuesta = await getTecnicos()
            setTecnicos(respuesta.data)
        } catch (error) {
            console.error('Error al obtener técnicos:', error)
        }
    }

    const obtenerDepartamentos = async () => {
        try {
            const respuesta = await getDepartamentos()
            setDepartamentos(respuesta.data)
        } catch (error) {
            console.error('Error al obtener departamentos:', error)
        }
    }

    const obtenerMunicipios = async (idDepartamento) => {
        try {
            const respuesta =
                await getMunicipiosPorDepartamento(idDepartamento)
            console.log("Municipios:", respuesta.data)
            setMunicipios(respuesta.data)
        } catch (error) {
            console.error("Error obteniendo municipios", error)
        }
    }

    useEffect(() => {
        obtenerTecnicos()
        obtenerDepartamentos()
    }, [])

    const limpiarFormulario = () => {
        setTecnico({
            nombres: '',
            apellidos: '',
            correo: '',
            telefono: '',
            especialidad: '',
            departamento: '',
            municipio: '',
            activo: true
        })

        setModoEdicion(false)
        setIdEditando(null)
    }

    const editarTecnico = (tecnicoSeleccionado) => {
        setTecnico({
            nombres: tecnicoSeleccionado.nombres,
            apellidos: tecnicoSeleccionado.apellidos,
            correo: tecnicoSeleccionado.correo,
            telefono: tecnicoSeleccionado.telefono,
            especialidad: tecnicoSeleccionado.especialidad,
            departamento: tecnicoSeleccionado.departamento,
            municipio: tecnicoSeleccionado.municipio,
            activo: tecnicoSeleccionado.activo
        })

        setIdEditando(tecnicoSeleccionado.idTecnico)
        setModoEdicion(true)
    }

    const guardarTecnico = async () => {
        try {
            if (!tecnico.nombres || !tecnico.apellidos || !tecnico.correo || !tecnico.telefono || !tecnico.especialidad) {
                alert('Por favor complete los campos obligatorios')
                return
            }

            console.log('Objeto técnico enviado:', tecnico)
            console.log('Modo edición:', modoEdicion)
            console.log('ID editando:', idEditando)
            console.log("========================");
            console.log("Objeto enviado:");
            console.log(tecnico);
            console.log("========================");

            if (modoEdicion) {
                await actualizarTecnico(idEditando, tecnico)
                console.log('Técnico actualizado correctamente')
            } else {
                await crearTecnico(tecnico)
                console.log('Técnico registrado correctamente')
            }

            limpiarFormulario()
            obtenerTecnicos()
        } catch (error) {
            console.error('Error al guardar técnico:', error)
        }
    }

    const borrarTecnico = async (idTecnico) => {

        const confirmar = window.confirm(
            '¿Está seguro de eliminar este técnico?'
        )

        if (!confirmar) {
            return
        }

        try {

            await eliminarTecnico(idTecnico)

            obtenerTecnicos()

            console.log('Técnico eliminado correctamente')

        } catch (error) {

            console.error('Error al eliminar técnico:', error)

        }

    }


    return (
        <section className="cliente-module">

            <div className="module-header">
                <div>
                    <span className="module-tag">Módulo Técnico</span>
                    <h2>Gestión de Técnicos</h2>
                    <p>
                        Registra, consulta y administra los técnicos del sistema,
                        integrando información de departamentos desde una API pública externa.
                    </p>
                </div>
            </div>

            <div className="card form-card">
                <h3>
                    {modoEdicion ? 'Editar Técnico' : 'Formulario Técnico'}
                </h3>

                <form className="cliente-form">

                    <div className="form-group">
                        <label>Nombres</label>
                        <input
                            type="text"
                            name="nombres"
                            value={tecnico.nombres}
                            onChange={handleChange}
                            placeholder="Ingrese los nombres"
                        />
                    </div>

                    <div className="form-group">
                        <label>Apellidos</label>
                        <input
                            type="text"
                            name="apellidos"
                            value={tecnico.apellidos}
                            onChange={handleChange}
                            placeholder="Ingrese los apellidos"
                        />
                    </div>

                    <div className="form-group">
                        <label>Correo</label>
                        <input
                            type="email"
                            name="correo"
                            value={tecnico.correo}
                            onChange={handleChange}
                            placeholder="Ingrese el correo"
                        />
                    </div>

                    <div className="form-group">
                        <label>Teléfono</label>
                        <input
                            type="text"
                            name="telefono"
                            value={tecnico.telefono}
                            onChange={handleChange}
                            placeholder="Ingrese el teléfono"
                        />
                    </div>

                    <div className="form-group">
                        <label>Especialidad</label>
                        <select
                            name="especialidad"
                            value={tecnico.especialidad}
                            onChange={handleChange}
                        >
                            <option value="">Seleccione una especialidad</option>

                            {especialidades.map((especialidad) => (
                                <option key={especialidad} value={especialidad}>
                                    {especialidad}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="form-group">
                        <label>Departamento</label>

                        <select
                            name="departamento"
                            value={tecnico.departamento}
                            onChange={(e) => {
                                const departamentoSeleccionado = departamentos.find(
                                    (dep) => dep.name === e.target.value
                                )

                                console.log("Departamento seleccionado:", departamentoSeleccionado)

                                setTecnico({
                                    ...tecnico,
                                    departamento: e.target.value,
                                    municipio: ''
                                })

                                if (departamentoSeleccionado) {
                                    obtenerMunicipios(departamentoSeleccionado.id)
                                }
                            }}
                        >
                            <option value="">Seleccione un departamento</option>

                            {departamentos.map((departamento) => (
                                <option
                                    key={departamento.id}
                                    value={departamento.name}
                                >
                                    {departamento.name}
                                </option>
                            ))}
                        </select>
                    </div>

                    <select
                        name="municipio"
                        value={tecnico.municipio}
                        onChange={handleChange}
                        disabled={!tecnico.departamento}
                    >
                        <option value="">
                            {tecnico.departamento
                                ? 'Seleccione un municipio'
                                : 'Seleccione primero un departamento'}
                        </option>

                        {municipios.map((municipio) => (
                            <option
                                key={municipio.id}
                                value={municipio.name}
                            >
                                {municipio.name}
                            </option>
                        ))}
                    </select>

                    <div className="form-group">
                        <label>Estado</label>
                        <select
                            name="activo"
                            value={tecnico.activo}
                            onChange={(e) =>
                                setTecnico({
                                    ...tecnico,
                                    activo: e.target.value === 'true'
                                })
                            }
                        >
                            <option value="true">Activo</option>
                            <option value="false">Inactivo</option>
                        </select>
                    </div>

                    <div className="form-actions">
                        <button
                            type="button"
                            className="btn-primary"
                            onClick={guardarTecnico}
                        >
                            {modoEdicion ? 'Actualizar' : 'Guardar'}
                        </button>

                        <button
                            type="button"
                            className="btn-secondary"
                            onClick={limpiarFormulario}
                        >
                            Limpiar
                        </button>
                    </div>

                </form>
            </div>

            <div className="card table-card">
                <div className="table-title">
                    <h3>Técnicos Registrados</h3>
                    <p>
                        Listado de técnicos consultados desde Spring Boot y almacenados en MySQL.
                    </p>
                </div>

                <div className="table-container">


                    <TecnicoTable
                        tecnicos={tecnicos}
                        onEditar={editarTecnico}
                        onEliminar={borrarTecnico}
                    />

                </div>


            </div>



        </section>
    )
}

export default Tecnico