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

// Chart.js | Entradas en diario (7w)
var ctx1_w = document.getElementById('chartEntryCountWeek').getContext('2d');
var chartEntryCountWeek = new Chart(ctx1_w, {
    type: 'bar',
    data: {
        labels: ['Entradas Totales', 'Entradas Diario', 'Revisiones'],
        datasets: [{
            label: 'Número de Entradas',
            data: [
                /* Thymeleaf dynamic data */
                [[${ statsEntryCountWeek.totalEntries }]],
                [[${ statsEntryCountWeek.totalEntriesJournal }]],
                [[${ statsEntryCountWeek.totalEntriesRevision }]]
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

// Chart.js | Entradas en diario (1m)
var ctx1_m = document.getElementById('chartEntryCountMonth').getContext('2d');
var chartEntryCountMonth = new Chart(ctx1_m, {
    type: 'bar',
    data: {
        labels: ['Entradas Totales', 'Entradas Diario', 'Revisiones'],
        datasets: [{
            label: 'Número de Entradas',
            data: [
                /* Thymeleaf dynamic data */
                [[${ statsEntryCountMonth.totalEntries }]],
                [[${ statsEntryCountMonth.totalEntriesJournal }]],
                [[${ statsEntryCountMonth.totalEntriesRevision }]]
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
var ctx1_dr = document.getElementById('chartEntryCountDateRange').getContext('2d');
var chartEntryCountDateRange = new Chart(ctx1_dr, {
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

var ctx2_j_1 = document.getElementById('chartActivityPerDayJournalAllTime').getContext('2d');
var chartActivityPerDayJournalAllTime = new Chart(ctx2_j_1, {
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

var ctx2_r_1 = document.getElementById('chartActivityPerDayRevisionAllTime').getContext('2d');
var chartActivityPerDayRevisionAllTime = new Chart(ctx2_r_1, {
    type: 'bar',
    data: {
        labels: ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'], // Days of the week in Spanish
        datasets: [{
            label: 'Actividad en revisiones por día',
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

// Chart.js | Actividad por dia Diario(7d)-->
// Data from Thymeleaf (all days)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalSundayEntryCount }]];

var ctx2_j_w = document.getElementById('chartActivityPerDayJournalWeek').getContext('2d');
var chartActivityPerDayJournalWeek = new Chart(ctx2_j_w, {
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

// Chart.js | Actividad por dia Revision (7d)-->
// Data from Thymeleaf (all days)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionSundayEntryCount }]];

var ctx2_r_w = document.getElementById('chartActivityPerDayRevisionWeek').getContext('2d');
var chartActivityPerDayRevisionDateRange = new Chart(ctx2_r_w, {
    type: 'bar',
    data: {
        labels: ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'], // Days of the week in Spanish
        datasets: [{
            label: 'Actividad en revisiones por día',
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

// Chart.js | Actividad por dia Diario(1m)-->
// Data from Thymeleaf (all days)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalSundayEntryCount }]];

var ctx2_j_m = document.getElementById('chartActivityPerDayJournalMonth').getContext('2d');
var chartActivityPerDayJournalMonth = new Chart(ctx2_j_m, {
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

// Chart.js | Actividad por dia Revision (1m)-->
// Data from Thymeleaf (all days)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionSundayEntryCount }]];

var ctx2_r_m = document.getElementById('chartActivityPerDayRevisionMonth').getContext('2d');
var chartActivityPerDayRevisionDateRange = new Chart(ctx2_r_m, {
    type: 'bar',
    data: {
        labels: ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'], // Days of the week in Spanish
        datasets: [{
            label: 'Actividad en revisiones por día',
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


// Chart.js | Actividad por dia Diario(DateRange)-->
// Data from Thymeleaf (all days)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalSundayEntryCount }]];

var ctx2_j_dr = document.getElementById('chartActivityPerDayJournalDateRange').getContext('2d');
var chartActivityPerDayJournalDateRange = new Chart(ctx2_j_dr, {
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

// Chart.js | Actividad por dia Revision (DateRange)-->
// Data from Thymeleaf (all days)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionSundayEntryCount }]];

var ctx2_r_dr = document.getElementById('chartActivityPerDayRevisionDateRange').getContext('2d');
var chartActivityPerDayRevisionDateRange = new Chart(ctx2_r_dr, {
    type: 'bar',
    data: {
        labels: ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'], // Days of the week in Spanish
        datasets: [{
            label: 'Actividad en revisiones por día',
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

// Chart.js | Frecuencia Emocional (All Time)-->
// Data from Thymeleaf (all days)
const emotionCounts = [
    [[${ statsEmotionFrequencyAllTime.emocionAlegriaCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionTristezaCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionIraCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionMiedoCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionAnsiedadCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionAmorCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionSorpresaCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionVerguenzaCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionFrustracionCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionSatisfaccionCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionAburrimientoCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionSerenidadCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionConfianzaCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionAbrumadoCount }]],
    [[${ statsEmotionFrequencyAllTime.emocionEsperanzaCount }]]
];

const emotionLabels = [
    "Alegría", "Tristeza", "Ira", "Miedo", "Ansiedad", "Amor", "Sorpresa",
    "Vergüenza", "Frustración", "Satisfacción", "Aburrimiento", "Serenidad",
    "Confianza", "Abrumado", "Esperanza"
];

// Get the context of the canvas element for Chart.js
const ctx3_1 = document.getElementById('emotionFrequencyPieChartAllTime').getContext('2d');

// Create the Chart.js pie chart
const emotionFrequencyPieChartAllTime = new Chart(ctx3_1, {
    type: 'pie',
    data: {
        labels: emotionLabels,
        datasets: [{
            label: 'Emotion Frequency',
            data: emotionCounts,
            backgroundColor: [
                '#FFEB3B', '#120966', '#940c3e', '#f77225', '#eb63d2', '#cf44b7', '#1f807c',
                '#D32F2F', '#824633', '#4CAF50', '#9E9E9E', '#4dd1e3',
                '#146b11', '#330505', '#89d134'
            ],
            borderColor: '#fff',
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                display: false,  // Hide the legend
            },
            tooltip: {
                callbacks: {
                    label: function (tooltipItem) {
                        const percentage = tooltipItem.raw / tooltipItem.chart.data.datasets[0].data.reduce((a, b) => a + b, 0) * 100;
                        return tooltipItem.label + ': ' + tooltipItem.raw + ' (' + percentage.toFixed(2) + '%)';
                    }
                }
            }
        }
    }
});
// Chart.js | Frecuencia Emocional (Date Range)-->
// Data from Thymeleaf (all days)
const emotionFrequencyCountsDateRange = [
    [[${ statsEmotionFrequencyDateRange.emocionAlegriaCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionTristezaCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionIraCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionMiedoCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionAnsiedadCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionAmorCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionSorpresaCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionVerguenzaCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionFrustracionCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionSatisfaccionCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionAburrimientoCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionSerenidadCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionConfianzaCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionAbrumadoCount }]],
    [[${ statsEmotionFrequencyDateRange.emocionEsperanzaCount }]]
];

const emotionLabels_dr = [
    "Alegría", "Tristeza", "Ira", "Miedo", "Ansiedad", "Amor", "Sorpresa",
    "Vergüenza", "Frustración", "Satisfacción", "Aburrimiento", "Serenidad",
    "Confianza", "Abrumado", "Esperanza"
];

// Get the context of the canvas element for Chart.js
const ctx3_dr = document.getElementById('emotionFrequencyPieChartDateRange').getContext('2d');

// Create the Chart.js pie chart
const emotionFrequencyPieChartDateRange = new Chart(ctx3_dr, {
    type: 'pie',
    data: {
        labels: emotionLabels_dr,
        datasets: [{
            label: 'Emotion Frequency',
            data: emotionFrequencyCountsDateRange,
            backgroundColor: [
                '#FFEB3B', '#120966', '#940c3e', '#f77225', '#eb63d2', '#cf44b7', '#1f807c',
                '#D32F2F', '#824633', '#4CAF50', '#9E9E9E', '#4dd1e3',
                '#146b11', '#330505', '#89d134'
            ],
            borderColor: '#fff',
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                display: false,  // Hide the legend
            },
            tooltip: {
                callbacks: {
                    label: function (tooltipItem) {
                        const percentage = tooltipItem.raw / tooltipItem.chart.data.datasets[0].data.reduce((a, b) => a + b, 0) * 100;
                        return tooltipItem.label + ': ' + tooltipItem.raw + ' (' + percentage.toFixed(2) + '%)';
                    }
                }
            }
        }
    }
});

// Chart.js | Frecuencia Emocional Last Week)-->
// Data from Thymeleaf (all days)
const emotionFrequencyCountsLastWeek = [
    [[${ statsEmotionFrequencyWeek.emocionAlegriaCount }]],
    [[${ statsEmotionFrequencyWeek.emocionTristezaCount }]],
    [[${ statsEmotionFrequencyWeek.emocionIraCount }]],
    [[${ statsEmotionFrequencyWeek.emocionMiedoCount }]],
    [[${ statsEmotionFrequencyWeek.emocionAnsiedadCount }]],
    [[${ statsEmotionFrequencyWeek.emocionAmorCount }]],
    [[${ statsEmotionFrequencyWeek.emocionSorpresaCount }]],
    [[${ statsEmotionFrequencyWeek.emocionVerguenzaCount }]],
    [[${ statsEmotionFrequencyWeek.emocionFrustracionCount }]],
    [[${ statsEmotionFrequencyWeek.emocionSatisfaccionCount }]],
    [[${ statsEmotionFrequencyWeek.emocionAburrimientoCount }]],
    [[${ statsEmotionFrequencyWeek.emocionSerenidadCount }]],
    [[${ statsEmotionFrequencyWeek.emocionConfianzaCount }]],
    [[${ statsEmotionFrequencyWeek.emocionAbrumadoCount }]],
    [[${ statsEmotionFrequencyWeek.emocionEsperanzaCount }]]
];

const emotionLabels_w = [
    "Alegría", "Tristeza", "Ira", "Miedo", "Ansiedad", "Amor", "Sorpresa",
    "Vergüenza", "Frustración", "Satisfacción", "Aburrimiento", "Serenidad",
    "Confianza", "Abrumado", "Esperanza"
];

// Get the context of the canvas element for Chart.js
const ctx3_w = document.getElementById('emotionFrequencyPieChartLastWeek').getContext('2d');

// Create the Chart.js pie chart
const emotionFrequencyPieChartLastWeek = new Chart(ctx3_w, {
    type: 'pie',
    data: {
        labels: emotionLabels_w,
        datasets: [{
            label: 'Emotion Frequency',
            data: emotionFrequencyCountsLastWeek,
            backgroundColor: [
                '#FFEB3B', '#120966', '#940c3e', '#f77225', '#eb63d2', '#cf44b7', '#1f807c',
                '#D32F2F', '#824633', '#4CAF50', '#9E9E9E', '#4dd1e3',
                '#146b11', '#330505', '#89d134'
            ],
            borderColor: '#fff',
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                display: false,  // Hide the legend
            },
            tooltip: {
                callbacks: {
                    label: function (tooltipItem) {
                        const percentage = tooltipItem.raw / tooltipItem.chart.data.datasets[0].data.reduce((a, b) => a + b, 0) * 100;
                        return tooltipItem.label + ': ' + tooltipItem.raw + ' (' + percentage.toFixed(2) + '%)';
                    }
                }
            }
        }
    }
});

// Chart.js | Frecuencia Emocional Last Week)-->
// Data from Thymeleaf (all days)
const emotionFrequencyCountsLastMonth = [
    [[${ statsEmotionFrequencyMonth.emocionAlegriaCount }]],
    [[${ statsEmotionFrequencyMonth.emocionTristezaCount }]],
    [[${ statsEmotionFrequencyMonth.emocionIraCount }]],
    [[${ statsEmotionFrequencyMonth.emocionMiedoCount }]],
    [[${ statsEmotionFrequencyMonth.emocionAnsiedadCount }]],
    [[${ statsEmotionFrequencyMonth.emocionAmorCount }]],
    [[${ statsEmotionFrequencyMonth.emocionSorpresaCount }]],
    [[${ statsEmotionFrequencyMonth.emocionVerguenzaCount }]],
    [[${ statsEmotionFrequencyMonth.emocionFrustracionCount }]],
    [[${ statsEmotionFrequencyMonth.emocionSatisfaccionCount }]],
    [[${ statsEmotionFrequencyMonth.emocionAburrimientoCount }]],
    [[${ statsEmotionFrequencyMonth.emocionSerenidadCount }]],
    [[${ statsEmotionFrequencyMonth.emocionConfianzaCount }]],
    [[${ statsEmotionFrequencyMonth.emocionAbrumadoCount }]],
    [[${ statsEmotionFrequencyMonth.emocionEsperanzaCount }]]
];

const emotionLabels_m = [
    "Alegría", "Tristeza", "Ira", "Miedo", "Ansiedad", "Amor", "Sorpresa",
    "Vergüenza", "Frustración", "Satisfacción", "Aburrimiento", "Serenidad",
    "Confianza", "Abrumado", "Esperanza"
];

// Get the context of the canvas element for Chart.js
const ctx3_m = document.getElementById('emotionFrequencyPieChartLastMonth').getContext('2d');

// Create the Chart.js pie chart
const emotionFrequencyPieChartLastMonth = new Chart(ctx3_m, {
    type: 'pie',
    data: {
        labels: emotionLabels_m,
        datasets: [{
            label: 'Emotion Frequency',
            data: emotionFrequencyCountsLastWeek,
            backgroundColor: [
                '#FFEB3B', '#120966', '#940c3e', '#f77225', '#eb63d2', '#cf44b7', '#1f807c',
                '#D32F2F', '#824633', '#4CAF50', '#9E9E9E', '#4dd1e3',
                '#146b11', '#330505', '#89d134'
            ],
            borderColor: '#fff',
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                display: false,  // Hide the legend
            },
            tooltip: {
                callbacks: {
                    label: function (tooltipItem) {
                        const percentage = tooltipItem.raw / tooltipItem.chart.data.datasets[0].data.reduce((a, b) => a + b, 0) * 100;
                        return tooltipItem.label + ': ' + tooltipItem.raw + ' (' + percentage.toFixed(2) + '%)';
                    }
                }
            }
        }
    }
});

