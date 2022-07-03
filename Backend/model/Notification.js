const mongoose = require("mongoose");

const notificationSchema = new mongoose.Schema({
    user_to_notify: {
        type: int,
        required: true,
        min: 1,
        max: 255,
    },
    user_who_cancelled_appointment: {
        type: int,
        required: true,
        min: 1,
        max: 255,
    },
    appointment_id: {
        type: int,
        required: true,
        min: 1,
        max: 255,
    },
    seen_by_user: {
        type: Boolean,
        required: true,
    },
    text: {
        type: String,
        required: true,
        min: 5,
        max: 255,
    },
});

module.exports = mongoose.model("Notification", notificationSchema);