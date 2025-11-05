const express = require('express');
const router = express.Router();
const arbitroController = require('../controllers/arbitro.controller');
const upload = require('../services/upload.local.service');

/**
 * @swagger
 * tags:
 *   name: Arbitros
 *   description: Gestión de árbitros
 */

/**
 * @swagger
 * /api/arbitros:
 *   get:
 *     summary: Obtiene una lista de árbitros.
 *     tags: [Arbitros]
 *     responses:
 *       200:
 *         description: Lista de árbitros obtenida con éxito.
 */
router.get('/', arbitroController.getArbitros);

/**
 * @swagger
 * /api/arbitros/{id}:
 *   get:
 *     summary: Obtiene un árbitro por su ID.
 *     tags: [Arbitros]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *     responses:
 *       200:
 *         description: Árbitro obtenido con éxito.
 *       404:
 *         description: Árbitro no encontrado.
 */
router.get('/:id', arbitroController.getArbitroById);

/**
 * @swagger
 * /api/arbitros/{id}/foto:
 *   post:
 *     summary: Sube o actualiza la foto de un árbitro.
 *     tags: [Arbitros]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *     requestBody:
 *       required: true
 *       content:
 *         multipart/form-data:
 *           schema:
 *             type: object
 *             properties:
 *               foto:
 *                 type: string
 *                 format: binary
 *     responses:
 *       200:
 *         description: Imagen subida con éxito.
 *       400:
 *         description: No se subió ningún archivo.
 *       404:
 *         description: Árbitro no encontrado.
 */
router.post('/:id/foto', upload.single('foto'), arbitroController.uploadArbitroFoto);

module.exports = router;
