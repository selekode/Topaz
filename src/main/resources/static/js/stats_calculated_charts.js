/* Topaz v0.02 | Charts created using Charts.js
*/
console.log("Script loaded");
// ENTRADAS EN EL DIARIO Y REVISION (TOTAL)
var ctx_EntryCountAllTime = document.getElementById('chartEntryCountAllTime').getContext('2d');
var chartEntryCountAllTime = new Chart(ctx_EntryCountAllTime, {
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

// ENTRADAS EN EL DIARIO Y REVISION (7d)
var ctx_EntryCountWeek = document.getElementById('chartEntryCountWeek').getContext('2d');
var chartEntryCountWeek = new Chart(ctx_EntryCountWeek, {
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

// ENTRADAS EN EL DIARIO Y REVISION (1m)
var ctx_EntryCountMonth = document.getElementById('chartEntryCountMonth').getContext('2d');
var chartEntryCountMonth = new Chart(ctx_EntryCountMonth, {
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

// ENTRADAS EN EL DIARIO Y REVISION (daterange)
var ctx_EntryCountDateRange = document.getElementById('chartEntryCountDateRange').getContext('2d');
var chartEntryCountDateRange = new Chart(ctx_EntryCountDateRange, {
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

// ACTIVIDAD POR DIA (DIARIO) (total)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.journalSundayEntryCount }]];

var ctx_ActivityPerDayJournalAllTime = document.getElementById('chartActivityPerDayJournalAllTime').getContext('2d');
var chartActivityPerDayJournalAllTime = new Chart(ctx_ActivityPerDayJournalAllTime, {
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

// ACTIVIDAD POR DIA (REVISION) (total)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekAllTime.revisionSundayEntryCount }]];

var ctx_ActivityPerDayRevisionAllTime = document.getElementById('chartActivityPerDayRevisionAllTime').getContext('2d');
var chartActivityPerDayRevisionAllTime = new Chart(ctx_ActivityPerDayRevisionAllTime, {
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

// ACTIVIDAD POR DIA (DIARIO) (7d)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekWeek.journalSundayEntryCount }]];

var ctx_ActivityPerDayJournalWeek = document.getElementById('chartActivityPerDayJournalWeek').getContext('2d');
var chartActivityPerDayJournalWeek = new Chart(ctx_ActivityPerDayJournalWeek, {
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

// ACTIVIDAD POR DIA (REVISION) (7d)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekWeek.revisionSundayEntryCount }]];

var ctx_ActivityPerDayRevisionWeek = document.getElementById('chartActivityPerDayRevisionWeek').getContext('2d');
var chartActivityPerDayRevisionDateRange = new Chart(ctx_ActivityPerDayRevisionWeek, {
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

// ACTIVIDAD POR DIA (DIARIO) (1m)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekMonth.journalSundayEntryCount }]];

var ctx_ActivityPerDayJournalMonth = document.getElementById('chartActivityPerDayJournalMonth').getContext('2d');
var chartActivityPerDayJournalMonth = new Chart(ctx_ActivityPerDayJournalMonth, {
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

// ACTIVIDAD POR DIA (REVISION) (1m)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekMonth.revisionSundayEntryCount }]];

var ctx_ActivityPerDayRevisionMonth = document.getElementById('chartActivityPerDayRevisionMonth').getContext('2d');
var chartActivityPerDayRevisionDateRange = new Chart(ctx_ActivityPerDayRevisionMonth, {
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


// ACTIVIDAD POR DIA (DIARIO) (DateRange)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.journalSundayEntryCount }]];

var ctx_ActivityPerDayJournalDateRange = document.getElementById('chartActivityPerDayJournalDateRange').getContext('2d');
var chartActivityPerDayJournalDateRange = new Chart(ctx_ActivityPerDayJournalDateRange, {
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

// ACTIVIDAD POR DIA (REVISION) (DateRange)
var journalMondayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionMondayEntryCount }]];
var journalTuesdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionTuesdayEntryCount }]];
var journalWednesdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionWednesdayEntryCount }]];
var journalThursdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionThursdayEntryCount }]];
var journalFridayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionFridayEntryCount }]];
var journalSaturdayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionSaturdayEntryCount }]];
var journalSundayEntryCount = [[${ statsActivityPerDayOfWeekDateRange.revisionSundayEntryCount }]];

var chartActivityPerDayRevisionDateRange = document.getElementById('chartActivityPerDayRevisionDateRange').getContext('2d');
var chartActivityPerDayRevisionDateRange = new Chart(chartActivityPerDayRevisionDateRange, {
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

// FRECUENCIA EMOCIONAL (All Time)
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

const ctx_emotionFrequencyPieChartAllTime = document.getElementById('emotionFrequencyPieChartAllTime').getContext('2d');

const emotionFrequencyPieChartAllTime = new Chart(ctx_emotionFrequencyPieChartAllTime, {
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
// FRECUENCIA EMOCIONAL (Date Range)
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

const ctx_emotionFrequencyPieChartDateRange = document.getElementById('emotionFrequencyPieChartDateRange').getContext('2d');

const emotionFrequencyPieChartDateRange = new Chart(ctx_emotionFrequencyPieChartDateRange, {
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

// FRECUENCIA EMOCIONAL (7d)
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

const ctx_emotionFrequencyPieChartLastWeek = document.getElementById('emotionFrequencyPieChartLastWeek').getContext('2d');

const emotionFrequencyPieChartLastWeek = new Chart(ctx_emotionFrequencyPieChartLastWeek, {
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

// FRECUENCIA EMOCIONAL (1m)
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

const ctx_emotionFrequencyPieChartLastMonth = document.getElementById('emotionFrequencyPieChartLastMonth').getContext('2d');

const emotionFrequencyPieChartLastMonth = new Chart(ctx_emotionFrequencyPieChartLastMonth, {
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

// ANÁLISIS PERSONAL: MEDIA DE VALORACIONES (AllTime) 
var averageRatingsAllTime = [
    [[${ statsRatingsAverageAllTime.valoracionDisciplina } != null ? ${ statsRatingsAverageAllTime.valoracionDisciplina } : 0]],
    [[${ statsRatingsAverageAllTime.valoracionOrden } != null ? ${ statsRatingsAverageAllTime.valoracionOrden } : 0]],
    [[${ statsRatingsAverageAllTime.valoracionImpulsividad } != null ? ${ statsRatingsAverageAllTime.valoracionImpulsividad } : 0]],
    [[${ statsRatingsAverageAllTime.valoracionConstancia } != null ? ${ statsRatingsAverageAllTime.valoracionConstancia } : 0]],
    [[${ statsRatingsAverageAllTime.valoracionTolerancia } != null ? ${ statsRatingsAverageAllTime.valoracionTolerancia } : 0]],
    [[${ statsRatingsAverageAllTime.valoracionControlPrepotencia } != null ? ${ statsRatingsAverageAllTime.valoracionControlPrepotencia } : 0]],
    [[${ statsRatingsAverageAllTime.valoracionHonestidad } != null ? ${ statsRatingsAverageAllTime.valoracionHonestidad } : 0]],
    [[${ statsRatingsAverageAllTime.valoracionAceptacion } != null ? ${ statsRatingsAverageAllTime.valoracionAceptacion } : 0]],
    [[${ statsRatingsAverageAllTime.valoracionConsecucionObjetivos } != null ? ${ statsRatingsAverageAllTime.valoracionConsecucionObjetivos } : 0]]
];

console.log('Average Ratings Data:', averageRatingsAllTime);

var data = {
    labels: ['Disciplina', 'Orden', 'Impulsividad', 'Constancia', 'Tolerancia', 'Control Prepotencia', 'Honestidad', 'Aceptacion', 'Consecucion Objetivos'],
    datasets: [{
        label: '',
        data: averageRatingsAllTime,  // Use the injected array data here
        backgroundColor: 'rgba(75, 192, 192, 0.2)', // Light green
        borderColor: 'rgba(75, 192, 192, 1)',
        borderWidth: 1
    }]
};

var config = {
    type: 'bar', // Bar chart type
    data: data,
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true,
                max: 10, // Set max value for Y axis
                ticks: {
                    color: 'white', // Make Y-axis labels white
                }
            },
            x: {
                ticks: {
                    color: 'white', // Make X-axis labels white
                }
            }
        },
        plugins: {
            legend: {
                labels: {
                    display: false,
                }
            }
        }
    }
};

var ratingsChart = new Chart(
    document.getElementById('chartAverageRatingsAllTime'),
    config
);

// ANÁLISIS PERSONAL: MEDIA DE VALORACIONES (7d) 
var averageRatings7d = [
    [[${ statsRatingsAverageWeek.valoracionDisciplina } != null ? ${ statsRatingsAverageWeek.valoracionDisciplina } : 0]],
    [[${ statsRatingsAverageWeek.valoracionOrden } != null ? ${ statsRatingsAverageWeek.valoracionOrden } : 0]],
    [[${ statsRatingsAverageWeek.valoracionImpulsividad } != null ? ${ statsRatingsAverageWeek.valoracionImpulsividad } : 0]],
    [[${ statsRatingsAverageWeek.valoracionConstancia } != null ? ${ statsRatingsAverageWeek.valoracionConstancia } : 0]],
    [[${ statsRatingsAverageWeek.valoracionTolerancia } != null ? ${ statsRatingsAverageWeek.valoracionTolerancia } : 0]],
    [[${ statsRatingsAverageWeek.valoracionControlPrepotencia } != null ? ${ statsRatingsAverageWeek.valoracionControlPrepotencia } : 0]],
    [[${ statsRatingsAverageWeek.valoracionHonestidad } != null ? ${ statsRatingsAverageWeek.valoracionHonestidad } : 0]],
    [[${ statsRatingsAverageWeek.valoracionAceptacion } != null ? ${ statsRatingsAverageWeek.valoracionAceptacion } : 0]],
    [[${ statsRatingsAverageWeek.valoracionConsecucionObjetivos } != null ? ${ statsRatingsAverageWeek.valoracionConsecucionObjetivos } : 0]]
];


console.log('Average Ratings Data:', averageRatings7d);

var data = {
    labels: ['Disciplina', 'Orden', 'Impulsividad', 'Constancia', 'Tolerancia', 'Control Prepotencia', 'Honestidad', 'Aceptacion', 'Consecucion Objetivos'],
    datasets: [{
        label: '',
        data: averageRatings7d,  // Use the injected array data here
        backgroundColor: 'rgba(75, 192, 192, 0.2)', // Light green
        borderColor: 'rgba(75, 192, 192, 1)',
        borderWidth: 1
    }]
};

var config = {
    type: 'bar', // Bar chart type
    data: data,
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true,
                max: 10, // Set max value for Y axis
                ticks: {
                    color: 'white', // Make Y-axis labels white
                }
            },
            x: {
                ticks: {
                    color: 'white', // Make X-axis labels white
                }
            }
        },
        plugins: {
            legend: {
                labels: {
                    display: false,
                }
            }
        }
    }
};

var ratingsChart = new Chart(
    document.getElementById('chartAverageRatings7d'),
    config
);

// ANÁLISIS PERSONAL: MEDIA DE VALORACIONES (1m) 
var averageRatings1m = [
    [[${ statsRatingsAverageMonth.valoracionDisciplina } != null ? ${ statsRatingsAverageMonth.valoracionDisciplina } : 0]],
    [[${ statsRatingsAverageMonth.valoracionOrden } != null ? ${ statsRatingsAverageMonth.valoracionOrden } : 0]],
    [[${ statsRatingsAverageMonth.valoracionImpulsividad } != null ? ${ statsRatingsAverageMonth.valoracionImpulsividad } : 0]],
    [[${ statsRatingsAverageMonth.valoracionConstancia } != null ? ${ statsRatingsAverageMonth.valoracionConstancia } : 0]],
    [[${ statsRatingsAverageMonth.valoracionTolerancia } != null ? ${ statsRatingsAverageMonth.valoracionTolerancia } : 0]],
    [[${ statsRatingsAverageMonth.valoracionControlPrepotencia } != null ? ${ statsRatingsAverageMonth.valoracionControlPrepotencia } : 0]],
    [[${ statsRatingsAverageMonth.valoracionHonestidad } != null ? ${ statsRatingsAverageMonth.valoracionHonestidad } : 0]],
    [[${ statsRatingsAverageMonth.valoracionAceptacion } != null ? ${ statsRatingsAverageMonth.valoracionAceptacion } : 0]],
    [[${ statsRatingsAverageMonth.valoracionConsecucionObjetivos } != null ? ${ statsRatingsAverageMonth.valoracionConsecucionObjetivos } : 0]]
];


console.log('Average Ratings Data:', averageRatings1m);

var data = {
    labels: ['Disciplina', 'Orden', 'Impulsividad', 'Constancia', 'Tolerancia', 'Control Prepotencia', 'Honestidad', 'Aceptacion', 'Consecucion Objetivos'],
    datasets: [{
        label: '',
        data: averageRatings1m,  // Use the injected array data here
        backgroundColor: 'rgba(75, 192, 192, 0.2)', // Light green
        borderColor: 'rgba(75, 192, 192, 1)',
        borderWidth: 1
    }]
};

var config = {
    type: 'bar', // Bar chart type
    data: data,
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true,
                max: 10, // Set max value for Y axis
                ticks: {
                    color: 'white', // Make Y-axis labels white
                }
            },
            x: {
                ticks: {
                    color: 'white', // Make X-axis labels white
                }
            }
        },
        plugins: {
            legend: {
                labels: {
                    display: false,
                }
            }
        }
    }
};

var ratingsChart = new Chart(
    document.getElementById('chartAverageRatings1m'),
    config
);

// ANÁLISIS PERSONAL: MEDIA DE VALORACIONES (1m) 
var averageRatingsDateRange = [
    [[${ statsRatingsAverageDateRange.valoracionDisciplina } != null ? ${ statsRatingsAverageDateRange.valoracionDisciplina } : 0]],
    [[${ statsRatingsAverageDateRange.valoracionOrden } != null ? ${ statsRatingsAverageDateRange.valoracionOrden } : 0]],
    [[${ statsRatingsAverageDateRange.valoracionImpulsividad } != null ? ${ statsRatingsAverageDateRange.valoracionImpulsividad } : 0]],
    [[${ statsRatingsAverageDateRange.valoracionConstancia } != null ? ${ statsRatingsAverageDateRange.valoracionConstancia } : 0]],
    [[${ statsRatingsAverageDateRange.valoracionTolerancia } != null ? ${ statsRatingsAverageDateRange.valoracionTolerancia } : 0]],
    [[${ statsRatingsAverageDateRange.valoracionControlPrepotencia } != null ? ${ statsRatingsAverageDateRange.valoracionControlPrepotencia } : 0]],
    [[${ statsRatingsAverageDateRange.valoracionHonestidad } != null ? ${ statsRatingsAverageDateRange.valoracionHonestidad } : 0]],
    [[${ statsRatingsAverageDateRange.valoracionAceptacion } != null ? ${ statsRatingsAverageDateRange.valoracionAceptacion } : 0]],
    [[${ statsRatingsAverageDateRange.valoracionConsecucionObjetivos } != null ? ${ statsRatingsAverageDateRange.valoracionConsecucionObjetivos } : 0]]
];


console.log('Average Ratings Data:', averageRatingsDateRange);

var data = {
    labels: ['Disciplina', 'Orden', 'Impulsividad', 'Constancia', 'Tolerancia', 'Control Prepotencia', 'Honestidad', 'Aceptacion', 'Consecucion Objetivos'],
    datasets: [{
        label: '',
        data: averageRatingsDateRange,  // Use the injected array data here
        backgroundColor: 'rgba(75, 192, 192, 0.2)', // Light green
        borderColor: 'rgba(75, 192, 192, 1)',
        borderWidth: 1
    }]
};

var config = {
    type: 'bar', // Bar chart type
    data: data,
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true,
                max: 10, // Set max value for Y axis
                ticks: {
                    color: 'white', // Make Y-axis labels white
                }
            },
            x: {
                ticks: {
                    color: 'white', // Make X-axis labels white
                }
            }
        },
        plugins: {
            legend: {
                labels: {
                    display: false,
                }
            }
        }
    }
};

var ratingsChart = new Chart(
    document.getElementById('chartAverageRatingsDateRange'),
    config
);