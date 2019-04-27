<template>
            <canvas ref="chart"></canvas>

  
</template>

<script>
    import Chart from 'chart.js';

    export default {
        name: 'app',
        props: {
            status  
        },
        mounted() {
            console.log(this.status);
            
            var chart = this.$refs.chart;
            var ctx = chart.getContext("2d");
            ctx.height = 200;
            var myChart;
            if(this.status == 0){
                myChart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: ["18.52", "18.53", "18.54", "18.55", "18.56", "18.56","18.57", "18.58", "18.59", "19.00", "19.01", "19.02"],
                        datasets: [{
                            label: 'User Heart Rate',
                            data: [60, 72, 65, 63, 66, 70, 69, 71, 62, 75, 70, 68],
                            backgroundColor: 'rgba(255, 159, 64, 0.2)',
                            borderColor: 'rgba(255, 206, 86, 1)',
                            borderWidth: 2,
                            lineTension: 0
                        }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    suggestedMax: 140,
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
                });
            }
            else{
                myChart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: ["18.52", "18.53", "18.54", "18.55", "18.56", "18.56","18.57", "18.58", "18.59", "19.00", "19.01", "19.02"],
                        datasets: [{
                            label: 'User Heart Rate',
                            data: [110, 99, 115, 105, 108, 100, 103, 114, 108, 122, 110, 112],
                            backgroundColor: 'rgba(255, 159, 64, 0.2)',
                            borderColor: 'rgba(255, 206, 86, 1)',
                            borderWidth: 2,
                            lineTension: 0
                        }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    suggestedMax: 140,
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
                });
            }
        }
    }

</script>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
    #content {
        margin: auto;
        width: 1024px;
        background-color: #FFFFFF;
        padding: 20px;
    }
</style>
