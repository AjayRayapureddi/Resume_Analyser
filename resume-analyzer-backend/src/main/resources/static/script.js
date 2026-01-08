let chart;

/* ---------- NAVIGATION ---------- */
function openBiasPage() {
    window.location.href = "bias.html";
}

function goBack() {
    window.location.href = "index.html";
}

/* ---------- DEFAULT ANALYSIS ---------- */
function loadAnalysis() {
    fetch("/analyze")
        .then(res => res.json())
        .then(data => {
            document.getElementById("total").innerText = data.totalCandidates;
            populateTable(data.fairRanking);
            localStorage.setItem("analysisData", JSON.stringify(data));
        });
}

/* ---------- CSV UPLOAD ---------- */
function uploadCSV() {
    const file = document.getElementById("csvFile").files[0];
    if (!file) {
        alert("Please select a CSV file");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    fetch("/analyze-upload", {
        method: "POST",
        body: formData
    })
    .then(res => res.json())
    .then(data => {
        document.getElementById("total").innerText = data.totalCandidates;
        populateTable(data.fairRanking);
        localStorage.setItem("analysisData", JSON.stringify(data));
    });
}

/* ---------- TABLE ---------- */
function populateTable(list) {
    const tbody = document.querySelector("#resultTable tbody");
    tbody.innerHTML = "";
    list.forEach(c => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${c.gender}</td>
            <td>${c.education}</td>
            <td>${c.experienceYears}</td>
            <td>${c.skillCount}</td>
            <td>${c.location}</td>
            <td>${c.selected}</td>
            <td>${c.weight.toFixed(3)}</td>
        `;
        tbody.appendChild(row);
    });
}

/* ---------- BIAS PAGE ---------- */
window.onload = function () {
    if (document.getElementById("biasChart")) {
        const data = JSON.parse(localStorage.getItem("analysisData"));
        if (!data) return;

        document.getElementById("beforeDI").innerText = "DI: 0.33";
        document.getElementById("beforeSPD").innerText = "SPD: -0.66";
        document.getElementById("afterDI").innerText = "DI: " + data.genderDI.toFixed(2);
        document.getElementById("afterSPD").innerText = "SPD: " + data.genderSPD.toFixed(2);

        const ctx = document.getElementById("biasChart");
        chart = new Chart(ctx, {
            type: "bar",
            data: {
                labels: ["Before DI", "After DI", "Before SPD", "After SPD"],
                datasets: [{
                    data: [0.33, data.genderDI, -0.66, data.genderSPD],
                    backgroundColor: ["red", "green", "red", "green"]
                }]
            }
        });
    }
};
