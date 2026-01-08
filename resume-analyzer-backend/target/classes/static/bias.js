const ctx = document.getElementById('biasChart').getContext('2d');

new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Disparate Impact', 'Statistical Parity Difference'],
        datasets: [
            {
                label: 'Before Fairness',
                data: [0.33, -0.66],
                backgroundColor: '#e74c3c'
            },
            {
                label: 'After Fairness',
                data: [0.33, -0.67],
                backgroundColor: '#1abc9c'
            }
        ]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'bottom',
                labels: {
                    color: '#ffffff'
                }
            }
        },
        scales: {
            y: {
                ticks: {
                    color: '#ffffff'
                }
            },
            x: {
                ticks: {
                    color: '#ffffff'
                }
            }
        }
    }
});

function goBack() {
    window.location.href = "index.html";
}
