const API_BASE = "http://localhost:8080/weather";

document.addEventListener("DOMContentLoaded", fetchAllReports);
document.getElementById("weather-form").addEventListener("submit", processSubmission);

// 1. GET ALL REPORTS -> Maps to your @GetMapping("/all")
async function fetchAllReports() {
    try {
        const response = await fetch("http://localhost:8080/all");
        const data = await response.json();
        const tbody = document.getElementById("weather-table-body");
        tbody.innerHTML = "";

        data.forEach(item => {
            // Visual badge mapping for condition display
            let badge = item.conditions === 'sunny' ? '☀️ Sunny' : item.conditions === 'cloudy' ? '☁️ Cloudy' : '🌧️ Rainy';
            
            tbody.innerHTML += `<tr>
                <td><b>#${item.weatherid}</b></td>
                <td>${item.city}</td>
                <td>${item.temperature}</td>
                <td>${badge}</td>
                <td>${item.date}</td>
                <td>
                    <button class="btn-edit" onclick="prepareEdit(${item.weatherid})">Edit</button>
                    <button class="btn-delete" onclick="executeDeletion(${item.weatherid})">Delete</button>
                </td>
            </tr>`;
        });
    } catch (error) {
        console.error("Error connecting to data pipeline:", error);
    }
}

// 2. INSERT OR UPDATE -> Maps to @PostMapping("/weather") and @PutMapping("/weather")
async function processSubmission(e) {
    e.preventDefault();
    const id = document.getElementById("weather-id").value;
    const city = document.getElementById("city").value;
    const temperature = document.getElementById("temperature").value;
    const conditions = document.getElementById("conditions").value;
    const date = document.getElementById("date").value;

    if (id) {
        // PUT execution utilizing query parameters to match your custom controller parameters
        try {
            const response = await fetch(`${API_BASE}?id=${id}&temp=${temperature}&condt=${conditions}`, {
                method: "PUT"
            });
            if (response.ok) {
                resetForm();
                fetchAllReports();
            }
        } catch (error) {
            console.error("Update execution failure:", error);
        }
    } else {
        // POST execution transmitting serialized JSON body layout
        const weatherPayload = { city, temperature, conditions, date };
        try {
            const response = await fetch(API_BASE, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(weatherPayload)
            });
            if (response.ok) {
                resetForm();
                fetchAllReports();
            }
        } catch (error) {
            console.error("Insertion execution failure:", error);
        }
    }
}

// 3. FETCH SINGLE RECORD TO FORM FOR EDIT -> Maps to @GetMapping("/weather")
async function prepareEdit(id) {
    try {
        const response = await fetch(`${API_BASE}?id=${id}`);
        const item = await response.json();

        document.getElementById("weather-id").value = item.weatherid;
        document.getElementById("city").value = item.city;
        document.getElementById("temperature").value = item.temperature;
        document.getElementById("conditions").value = item.conditions;
        document.getElementById("date").value = item.date;

        // Freeze fields that are not supported by your update routine logic
        document.getElementById("city").disabled = true;
        document.getElementById("date").disabled = true;

        document.getElementById("form-title").innerText = "Edit Weather Metrics";
        document.getElementById("cancel-btn").classList.remove("hidden");
    } catch (error) {
        console.error("Error pulling profile specs:", error);
    }
}

// 4. DELETE OPERATION -> Maps to @DeleteMapping("/weather")
async function executeDeletion(id) {
    if (confirm("Purge this metric entry permanently from database records?")) {
        try {
            const response = await fetch(`${API_BASE}?id=${id}`, {
                method: "DELETE"
            });
            if (response.ok) {
                fetchAllReports();
            }
        } catch (error) {
            console.error("Deletion lifecycle failure:", error);
        }
    }
}

function resetForm() {
    document.getElementById("weather-form").reset();
    document.getElementById("weather-id").value = "";
    document.getElementById("city").disabled = false;
    document.getElementById("date").disabled = false;
    document.getElementById("form-title").innerText = "Insert Weather Report";
    document.getElementById("cancel-btn").classList.add("hidden");
}
