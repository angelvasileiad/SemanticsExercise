package ForthTask;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.config.RepositoryConfig;
import org.eclipse.rdf4j.repository.config.RepositoryImplConfig;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;
import org.eclipse.rdf4j.repository.sail.config.SailRepositoryConfig;
import org.eclipse.rdf4j.sail.config.SailImplConfig;
import org.eclipse.rdf4j.sail.inferencer.fc.config.DedupingInferencerConfig;
import org.eclipse.rdf4j.sail.inferencer.fc.config.SchemaCachingRDFSInferencerConfig;
import org.eclipse.rdf4j.sail.memory.config.MemoryStoreConfig;
import org.eclipse.rdf4j.sail.spin.config.SpinSailConfig;

import static Auxiliary.AuxiliaryConstants.GRAPHDB_SERVER;
import static Auxiliary.AuxiliaryConstants.REPOSITORY_ID;

/**
 * This class represents the execution of the forth task of the Semantics Exercise.
 *
 * @author  Vasileiadis Angelos
 * @since   Q2_2021
 * @version Q2_2021
 */
public class ForthTaskExecution {

    public static void main(String[] args) {

        // Create a repository manager
        RepositoryManager manager = new RemoteRepositoryManager(GRAPHDB_SERVER);

        // Create the configuration for the sail stack
        SailImplConfig spinSailConfig = new SpinSailConfig(
                new SchemaCachingRDFSInferencerConfig(
                        new DedupingInferencerConfig(new MemoryStoreConfig()))
        );

        RepositoryImplConfig repositoryTypeSpec = new SailRepositoryConfig(spinSailConfig);

        // Create the configuration for the actual repository
        RepositoryConfig repConfig = new RepositoryConfig(REPOSITORY_ID, repositoryTypeSpec);
        manager.addRepositoryConfig(repConfig);

        // Get the Repository from the manager
        Repository repository = manager.getRepository(REPOSITORY_ID);


    }
}
