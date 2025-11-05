const multer = require('multer');
const path = require('path');

// Configure multer for local disk storage
const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        // The destination folder for uploads
        cb(null, 'uploads/');
    },
    filename: function (req, file, cb) {
        // Create a unique file name
        const uniqueSuffix = Date.now() + '-' + Math.round(Math.random() * 1E9);
        cb(null, 'arbitro-' + req.params.id + '-' + uniqueSuffix + path.extname(file.originalname));
    }
});

// Optional: Add file filter to accept only images
const fileFilter = (req, file, cb) => {
    const filetypes = /jpeg|jpg|png/;
    const mimetype = filetypes.test(file.mimetype);
    const extname = filetypes.test(path.extname(file.originalname).toLowerCase());
    if (mimetype && extname) {
        return cb(null, true);
    }
    cb("Error: La carga de archivos solo soporta los siguientes tipos de archivo - " + filetypes);
};

const upload = multer({ 
    storage: storage,
    fileFilter: fileFilter
});

module.exports = upload;
