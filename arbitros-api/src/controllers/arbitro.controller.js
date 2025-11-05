const arbitroService = require('../services/arbitro.service');

const getArbitros = (req, res) => {
    const arbitros = arbitroService.getAllArbitros();
    res.json(arbitros);
};

const getArbitroById = (req, res) => {
    const id = parseInt(req.params.id, 10);
    const arbitro = arbitroService.getArbitroById(id);
    if (arbitro) {
        res.json(arbitro);
    } else {
        res.status(404).json({ message: 'Árbitro no encontrado' });
    }
};

const uploadArbitroFoto = (req, res) => {
    if (!req.file) {
        return res.status(400).json({ message: 'No se subió ningún archivo.' });
    }

    const id = parseInt(req.params.id, 10);

    // Construct the URL for the locally stored file
    const fotoUrl = `${req.protocol}://${req.get('host')}/uploads/${req.file.filename}`;

    try {
        const updatedArbitro = arbitroService.updateArbitroFoto(id, fotoUrl);
        res.json({ 
            message: 'Imagen subida con éxito', 
            fotoUrl: fotoUrl,
            arbitro: updatedArbitro
        });
    } catch (error) {
        res.status(404).json({ message: error.message });
    }
};

module.exports = {
    getArbitros,
    getArbitroById,
    uploadArbitroFoto,
};