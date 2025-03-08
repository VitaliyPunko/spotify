package vpunko.spotify.core.jobs;

import org.jobrunr.jobs.JobId;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.annotations.Recurring;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private final JobScheduler jobScheduler;
//    private final SampleJobService sampleService;

    public JobController(JobScheduler jobScheduler) {
        this.jobScheduler = jobScheduler;
    }

    @GetMapping("/enqueue-example-job")
    public String enqueueExampleJob(@RequestParam(value = "name", defaultValue = "World") String name) {
        final JobId enqueuedJobId = jobScheduler.enqueue(() -> {
            String s = "Hello " + name;
            System.out.println(s);
        });

        return "Job Enqueued: " + enqueuedJobId.toString();
    }

    //todo: try https://www.jobrunr.io/en/documentation/background-methods/
    // MyJobRequestHandler and run method
    @Recurring(id = "my-recurring-job", cron = "*/100 * * * *")
    @Job(name = "My recurring job")
    public void executeSampleJob() {
        System.out.println("Cron job");
        // your business logic here
    }
}
