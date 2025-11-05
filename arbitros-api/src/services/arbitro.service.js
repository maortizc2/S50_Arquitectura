const fs = require('fs');
const path = require('path');

const arbitrosFilePath = path.join(__dirname, '../data/arbitros.json');

const readArbitrosFromFile = () => {
    const data = fs.readFileSync(arbitrosFilePath);
    return JSON.parse(data);
};

const writeArbitrosToFile = (arbitros) => {
    fs.writeFileSync(arbitrosFilePath, JSON.stringify(arbitros, null, 2));
};

const getAllArbitros = () => {
    return readArbitrosFromFile();
};

const getArbitroById = (id) => {
    const arbitros = readArbitrosFromFile();
    return arbitros.find(a => a.id === id);
};

const updateArbitroFoto = (id, fotoUrl) => {
    const arbitros = readArbitrosFromFile();
    const arbitroIndex = arbitros.findIndex(a => a.id === id);

    if (arbitroIndex === -1) {
        throw new Error('Árbitro no encontrado');
    }

    arbitros[arbitroIndex].fotoUrl = fotoUrl;
    writeArbitrosToFile(arbitros);
    return arbitros[arbitroIndex];
};

module.exports = {
    getAllArbitros,
    getArbitroById,
    updateArbitroFoto,
};