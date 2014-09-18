package pl.warsjawa.microservice.config

import com.ofg.config.BasicProfiles
import de.flapdoodle.embed.mongo.Command
import de.flapdoodle.embed.mongo.MongodExecutable
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.*
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.config.IRuntimeConfig
import de.flapdoodle.embed.process.io.directories.FixedPath
import de.flapdoodle.embed.process.runtime.Network
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@TypeChecked
@Configuration
@Slf4j
@Profile([BasicProfiles.PRODUCTION, BasicProfiles.DEVELOPMENT])
class EmbeddedMongoConfiguration {

    @Bean(initMethod = 'start')
    MongodExecutable mongodExecutable(@Value('${mongodb.port:27017}') Integer port,
                                      @Value('${mongodb.db.path:mongodb/}') String dbPath,
                                      @Value('${mongodb.db.artifactstore.path:artifactstore/}') File artifactStorePath) {
        IRuntimeConfig runtimeConfig = buildRuntimeConfig(getPathToArtifactStore(artifactStorePath))
        MongodStarter runtime = MongodStarter.getInstance(runtimeConfig);
        IMongodConfig mongodConfig = buildMongodConfig(port, dbPath)
        return runtime.prepare(mongodConfig)
    }

    private String getPathToArtifactStore(File artifactStore) {
        if(artifactStore.exists()) {
            return artifactStore.absolutePath
        }

        String object = artifactStore.mkdirs() ? artifactStore.absolutePath : System.properties['java.io.tmpdir']
        log.info("artifactStore path: $object")

        return object
    }

    private IRuntimeConfig buildRuntimeConfig(String artifactStorePath) {
        Command command = Command.MongoD;
        return new RuntimeConfigBuilder()
                .defaults(command)
                .artifactStore(new ArtifactStoreBuilder()
                .defaults(command)
                .tempDir(new FixedPath(artifactStorePath)))
                .build()
    }

    private IMongodConfig buildMongodConfig(int port, String dbPath) {
        return new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, Network.localhostIsIPv6()))
                .cmdOptions(new MongoCmdOptionsBuilder()
                .syncDelay(10)
                .useNoPrealloc(false)
                .useSmallFiles(true)
                .useNoJournal(false)
                .build())
                .replication(new Storage(dbPath, null, 0))
                .build()
    }

}

