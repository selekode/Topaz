/* Topaz v0.02 | Charts created using Charts.js
 */

// Chart.js | Entradas en diario (total)

var ctx1 = document.getElementById('chartEntryCountAllTime').getContext('2d');
var chartEntryCountAllTime = new Chart(ctx1, {
    type: 'bar',
    data: {
        labels: ['Entradas Totales', 'Entradas Diario', 'Revisiones'],
        datasets: [{
            label: 'Número de Entradas',
            data: [
                /* Thymeleaf dynamic data */
                [[${ statsEntryCountAlltime.totalEntries }]],
                [[${ statsEntryCountAlltime.totalEntriesJournal }]],
                [[${ statsEntryCountAlltime.totalEntriesRevision }]]
            ],
            backgroundColor: ['rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)'],
            borderColor: ['rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)'],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            x: {
                ticks: {
                    color: 'white' // X-axis labels color
                }
            },
            y: {
                beginAtZero: true,
                ticks: {
                    color: 'white' // Y-axis labels color
                }
            }
        },
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
                labels: {
                    color: 'white' // Legend text color
                }
            },
            title: {
                display: false,
                text: 'Entradas totales',
                color: 'white' // Title color (if enabled)
            }
        }
    }
});


// Chart.js | Entradas en diario (daterange)-->
var ctx2 = document.getElementById('chartEntryCountDateRange').getContext('2d');
var chartEntryCountDateRange = new Chart(ctx2, {
    type: 'bar',
    data: {
        labels: ['Entradas Totales', 'Entradas Diario', 'Revisiones'],
        datasets: [{
            label: 'Número de Entradas',
            data: [
                /* Thymeleaf dynamic data */
                [[${ statsEntryCountDateRange.totalEntries }]],
                [[${ statsEntryCountDateRange.totalEntriesJournal }]],
                [[${ statsEntryCountDateRange.totalEntriesRevision }]]
            ],
            backgroundColor: ['rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)'],
            borderColor: ['rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)'],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            x: {
                ticks: {
                    color: 'white' // X-axis labels color
                }
            },
            y: {
                beginAtZero: true,
                ticks: {
                    color: 'white' // Y-axis labels color
                }
            }
        },
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
                labels: {
                    color: 'white' // Legend text color
                }
            },
            title: {
                display: false,
                text: 'Entradas totales',
                color: 'white' // Title color (if enabled)
            }
        }
    }
});


// Chart.js | Actividad por dia Diario(total)-->
// Data from Thymeleaf (all days)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalSundayEntryCount }]];

var ctx3 = document.getElementById('chartActivityPerDayJournalAllTime').getContext('2d');
var chartActivityPerDayJournalAllTime = new Chart(ctx3, {
    type: 'bar',
    data: {
        labels: ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'], // Days of the week in Spanish
        datasets: [{
            label: 'Actividad en el diario por día',
            data: [
                journalMondayEntryCount,
                journalTuesdayEntryCount,
                journalWednesdayEntryCount,
                journalThursdayEntryCount,
                journalFridayEntryCount,
                journalSaturdayEntryCount,
                journalSundayEntryCount
            ], // Data for each day
            backgroundColor: 'rgba(35, 163, 187, 0.5)', // Bar color
            borderColor: 'rgba(35, 163, 187, 1)', // Border color
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true,
                ticks: {
                    color: 'white' // Set Y-axis ticks color to white
                }
            },
            x: {
                ticks: {
                    color: 'white' // Set X-axis ticks color to white
                }
            }
        },
        plugins: {
            legend: {
                labels: {
                    color: 'white' // Set legend text color to white
                }
            }
        },
        elements: {
            bar: {
                borderColor: 'white' // Set bar border color to white
            }
        }
    }
});



// Chart.js | Actividad por dia Revision (total)-->
// Data from Thymeleaf (all days)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionSundayEntryCount }]];

var ctx3 = document.getElementById('chartActivityPerDayRevisionAllTime').getContext('2d');
var chartActivityPerDayRevisionAllTime = new Chart(ctx3, {
    type: 'bar',
    data: {
        labels: ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'], // Days of the week in Spanish
        datasets: [{
            label: 'Actividad en el diario por día',
            data: [
                journalMondayEntryCount,
                journalTuesdayEntryCount,
                journalWednesdayEntryCount,
                journalThursdayEntryCount,
                journalFridayEntryCount,
                journalSaturdayEntryCount,
                journalSundayEntryCount
            ], // Data for each day
            backgroundColor: 'rgba(35, 163, 187, 0.5)', // Bar color
            borderColor: 'rgba(35, 163, 187, 1)', // Border color
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true,
                ticks: {
                    color: 'white' // Set Y-axis ticks color to white
                }
            },
            x: {
                ticks: {
                    color: 'white' // Set X-axis ticks color to white
                }
            }
        },
        plugins: {
            legend: {
                labels: {
                    color: 'white' // Set legend text color to white
                }
            }
        },
        elements: {
            bar: {
                borderColor: 'white' // Set bar border color to white
            }
        }
    }
});
