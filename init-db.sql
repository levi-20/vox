SELECT 'CREATE DATABASE vox'
    WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'vox')\gexec